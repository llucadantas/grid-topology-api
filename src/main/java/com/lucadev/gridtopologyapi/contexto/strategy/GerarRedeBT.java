package com.lucadev.gridtopologyapi.contexto.strategy;

import com.lucadev.gridtopologyapi.contexto.ContextoRede;
import com.lucadev.gridtopologyapi.poste.Poste;
import com.lucadev.gridtopologyapi.transformador.Transformador;
import com.lucadev.gridtopologyapi.trechobt.TrechoBT;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class GerarRedeBT implements GerarRede {

    private final Random random = new Random();

    @Override
    public void gerarContexto(ContextoRede rede) {
        List<Transformador> transformadores = rede.getTransformadores();
        List<Poste> postesDesconectados = new ArrayList<>(rede.getPostesBT());

        // Validação de segurança para evitar exceções se a lista estiver vazia
        if (transformadores == null || transformadores.isEmpty() || postesDesconectados.isEmpty()) {
            return;
        }

        // 1. Mapeia a sub-rede de cada Transformador.
        // Inicialmente, a sub-rede contém apenas o poste onde o próprio transformador está montado.
        Map<Transformador, List<Poste>> subredesPorTx = new HashMap<>();
        for (Transformador tx : transformadores) {
            List<Poste> postesAtendidos = new ArrayList<>();
            postesAtendidos.add(tx.getPoste());
            subredesPorTx.put(tx, postesAtendidos);
        }

        // 2. Loop para distribuir todos os postes restantes entre os transformadores disponíveis
        while (!postesDesconectados.isEmpty()) {
            // Sorteia um dos transformadores da rede para expandir a baixa tensão dele
            Transformador txSorteado = transformadores.get(random.nextInt(transformadores.size()));
            List<Poste> postesConectadosAoTx = subredesPorTx.get(txSorteado);

            // Sorteia um poste que JÁ recebe energia desse transformador como Ponto de Origem
            Poste posteOrigem = postesConectadosAoTx.get(random.nextInt(postesConectadosAoTx.size()));

            // Sorteia e remove um poste sem energia como Ponto de Destino
            int indexDestino = random.nextInt(postesDesconectados.size());
            Poste posteDestino = postesDesconectados.remove(indexDestino);

            // Instancia a aresta da rede de Baixa Tensão (TrechoBT)
            TrechoBT novoTrechoBt = new TrechoBT();
            //novoTrechoBt.setCodigo("TR-BT-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
            novoTrechoBt.setTransformador(txSorteado); // Amarração da chave estrangeira (FK)
            novoTrechoBt.setPosteOrigem(posteOrigem);
            novoTrechoBt.setPosteDestino(posteDestino);

            // Adiciona o trecho gerado no contexto e atualiza a sub-rede do transformador
            rede.getTrechosBT().add(novoTrechoBt);
            postesConectadosAoTx.add(posteDestino);
        }
    }
}