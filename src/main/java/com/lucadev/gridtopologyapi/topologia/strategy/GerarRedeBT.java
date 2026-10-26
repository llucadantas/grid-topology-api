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
public class GerarRedeBT implements GerarRede {

    private final Random random = new Random();
    private static final Logger logger = LoggerFactory.getLogger(GerarRedeBT.class);

    @Override
    public void gerarContexto(Topologia rede) {
        List<Transformador> transformadores = rede.getTransformadores() != null ? rede.getTransformadores() : new ArrayList<>();
        List<Poste> postesRede = rede.getPostesBT() != null ? rede.getPostesBT() : new ArrayList<>();

        if (transformadores.isEmpty() || postesRede.isEmpty()) {
            logger.warn("Sem transformadores ou postes suficientes para gerar rede BT.");
            return;
        }

        List<Poste> postesDesconectados = new ArrayList<>(postesRede);

        if (rede.getTrechosBT() == null) {
            rede.setTrechosBT(new ArrayList<>());
        }

        Map<Transformador, List<Poste>> subredesPorTx = new HashMap<>();
        // NOVA LISTA: Apenas transformadores aptos para sorteio
        List<Transformador> transformadoresValidos = new ArrayList<>();

        for (Transformador tx : transformadores) {
            Poste posteDoTx = tx.getPoste();

            // Removida a exigência do ID ser diferente de null
            if (posteDoTx != null) {
                List<Poste> postesAtendidos = new ArrayList<>();
                postesAtendidos.add(posteDoTx);
                subredesPorTx.put(tx, postesAtendidos);

                transformadoresValidos.add(tx);

                // Remove o poste da lista de desconectados comparando o objeto em si
                // Como tudo foi gerado em memória na mesma execução, as instâncias são as mesmas
                postesDesconectados.remove(posteDoTx);
            }
        }

        // Se nenhum transformador tiver poste, aborta para evitar loop infinito
        if (transformadoresValidos.isEmpty()) {
            logger.warn("Nenhum transformador válido (com poste) encontrado. Abortando.");
            return;
        }

        while (!postesDesconectados.isEmpty()) {
            // CORREÇÃO: Sorteia APENAS da lista de transformadores válidos
            Transformador txSorteado = transformadoresValidos.get(random.nextInt(transformadoresValidos.size()));

            // Agora é 100% garantido que isso não virá nulo nem vazio
            List<Poste> postesConectadosAoTx = subredesPorTx.get(txSorteado);

            // Sorteia a origem e remove o destino
            Poste posteOrigem = postesConectadosAoTx.get(random.nextInt(postesConectadosAoTx.size()));
            int indexDestino = random.nextInt(postesDesconectados.size());
            Poste posteDestino = postesDesconectados.remove(indexDestino); // Esvazia a lista obrigatoriamente

            // Instancia a aresta
            TrechoBT novoTrechoBt = new TrechoBT();
            novoTrechoBt.setTransformador(txSorteado);
            novoTrechoBt.setPosteOrigem(posteOrigem);
            novoTrechoBt.setPosteDestino(posteDestino);

            // Atualiza
            rede.getTrechosBT().add(novoTrechoBt);
            postesConectadosAoTx.add(posteDestino);
        }

        logger.info("Trechos BT gerados com sucesso: {}", rede.getTrechosBT().size());
    }
}