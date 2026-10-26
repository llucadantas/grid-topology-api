package com.lucadev.gridtopologyapi.contexto.strategy;

import com.lucadev.gridtopologyapi.contexto.ContextoRede;
import com.lucadev.gridtopologyapi.poste.Poste;
import com.lucadev.gridtopologyapi.transformador.Transformador;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class GerarTransformadores implements GerarRede {

    @Override
    public void gerarContexto(ContextoRede rede) {
        // Quantidade de transformadores (10% do total de postes do projeto)
        int qntdTransformadores = (int) (rede.getPostes().size() * 0.1);

        // 1. Pegamos a lista de postes que fazem parte da Média Tensão
        List<Poste> postesDisponiveisMT = new ArrayList<>(rede.getPostesMT());

        if (postesDisponiveisMT.isEmpty() || qntdTransformadores == 0) return;

        // 2. Embaralha a lista. Isso garante aleatoriedade e impede repetição (cada poste é único na lista)
        Collections.shuffle(postesDisponiveisMT);

        // Proteção: Garante que não vai tentar criar mais transformadores do que postes disponíveis
        int limite = Math.min(qntdTransformadores, postesDisponiveisMT.size());

//        Random random = new Random();
        // Potências comerciais reais para deixar o mock profissional
        //double[] potenciasComuns = {30.0, 45.0, 75.0, 112.5, 150.0};

        for (int i = 0; i < limite; i++) {
            // Como a lista está embaralhada, pegar do índice 0 até o 'limite' já nos dá itens aleatórios
            Poste posteSorteado = postesDisponiveisMT.get(i);

            Transformador tx = new Transformador();
            //tx.setCodigo("TX-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());

            // Sorteia uma potência real do array
//            double potencia = potenciasComuns[random.nextInt(potenciasComuns.length)];
//            tx.setPotenciaKva(potencia);
//
//            tx.setTensaoPrimariaKv(13.8); // 13.8 kV (Média Tensão padrão no Brasil)
//            tx.setTensaoSecundariaV(220.0); // 220 V (Baixa Tensão)

            tx.setPoste(posteSorteado);

            // 3. Adiciona o transformador gerado no contexto geral da rede
            rede.getTransformadores().add(tx);
        }
    }
}