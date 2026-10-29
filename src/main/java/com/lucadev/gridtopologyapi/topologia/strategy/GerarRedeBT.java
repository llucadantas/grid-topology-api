package com.lucadev.gridtopologyapi.topologia.strategy;

import com.lucadev.gridtopologyapi.poste.Poste;
import com.lucadev.gridtopologyapi.topologia.Topologia;
import com.lucadev.gridtopologyapi.transformador.Transformador;
import com.lucadev.gridtopologyapi.trechobt.TrechoBT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
@Order(4)
public class GerarRedeBT {

    private final Random random = new Random();
    private static final Logger logger = LoggerFactory.getLogger(GerarRedeBT.class);

    public void gerarContexto(Topologia rede) {
        List<Transformador> transformadores = rede.getTransformadores();
        // Puxa do bolsão geral de postes do projeto
        List<Poste> postesRede = rede.getPostesMT();

        if (transformadores.isEmpty() || postesRede.isEmpty()) {
            logger.warn("Sem transformadores ou postes suficientes para gerar rede BT.");
            return;
        }

        List<Poste> postesDesconectados = rede.getPostesBT();

        Map<Transformador, List<Poste>> subredesPorTx = new HashMap<>();
        List<Transformador> transformadoresValidos = new ArrayList<>();

        for (Transformador tx : transformadores) {
            Poste posteDoTx = tx.getPoste();

            if (posteDoTx != null) {
                List<Poste> postesAtendidos = new ArrayList<>();
                postesAtendidos.add(posteDoTx);
                subredesPorTx.put(tx, postesAtendidos);
                transformadoresValidos.add(tx);

                // Força o Java a usar a referência de memória para não se perder com IDs nulos
                postesDesconectados.removeIf(p -> p == posteDoTx);
            }
        }

        if (transformadoresValidos.isEmpty()) {
            logger.warn("Nenhum transformador válido (com poste) encontrado. Abortando.");
            return;
        }

        while (!postesDesconectados.isEmpty()) {

            // 1. Seleciona o transformador com menos trechos BT gerados
            Transformador txEscolhido = null;
            int menorCarga = Integer.MAX_VALUE;

            for (Transformador tx : transformadoresValidos) {

                int trechosGerados = (int) rede.getTrechosBT()
                        .stream()
                        .filter(trecho -> trecho.getTransformador().equals(tx))
                        .count();

                if (trechosGerados < menorCarga) {
                    menorCarga = trechosGerados;
                    txEscolhido = tx;
                }
            }

            // 2. Obtém os postes já atendidos pelo transformador escolhido
            List<Poste> postesConectadosAoTx = subredesPorTx.get(txEscolhido);

            // 3. Sorteia uma origem já conectada e um destino desconectado
            Poste posteOrigem = postesConectadosAoTx.get(
                    random.nextInt(postesConectadosAoTx.size())
            );

            int indexDestino = random.nextInt(postesDesconectados.size());
            Poste posteDestino = postesDesconectados.remove(indexDestino);

            // 4. Cria o trecho BT
            TrechoBT novoTrechoBt = new TrechoBT();
            novoTrechoBt.setTransformador(txEscolhido);
            novoTrechoBt.setPosteOrigem(posteOrigem);
            novoTrechoBt.setPosteDestino(posteDestino);

            rede.getTrechosBT().add(novoTrechoBt);

            // 5. Atualiza a sub-rede do transformador
            postesConectadosAoTx.add(posteDestino);
        }

        logger.info("Trechos BT gerados com sucesso: {}", rede.getTrechosBT().size());
        logger.info("Total de Transformadores gerados na rede: {}", transformadores.size());
        logger.info("Transformadores válidos para o sorteio BT: {}", transformadoresValidos.size());
    }
}