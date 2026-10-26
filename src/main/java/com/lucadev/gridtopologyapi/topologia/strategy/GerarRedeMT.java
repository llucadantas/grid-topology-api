package com.lucadev.gridtopologyapi.topologia.strategy;

import com.lucadev.gridtopologyapi.topologia.Topologia;
import com.lucadev.gridtopologyapi.poste.Poste;
import com.lucadev.gridtopologyapi.trechomt.TrechoMT;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Order(2)
public class GerarRedeMT implements GerarRede {
    @Override
    public void gerarContexto(Topologia rede) {
        int x = 30;
        int y = 50;
        Random random = new Random();
        int numeroAleatorio = random.nextInt((y - x) + 1) + x;

        List<Poste> todosPostes = rede.getPostes();
        if (todosPostes.isEmpty()) return;

        List<Poste> redeConectada = new ArrayList<>();
        List<Poste> postesDesconectados = new ArrayList<>(todosPostes);

        redeConectada.add(postesDesconectados.removeFirst());
        int limitePostesMT = (int) (rede.getQntdPoste() * (numeroAleatorio / 100.0));

        while (redeConectada.size() < limitePostesMT) {
            Poste posteOrigem = redeConectada.get(random.nextInt(redeConectada.size()));

            Poste posteDestino = postesDesconectados.remove(random
                    .nextInt(postesDesconectados.size()));

            TrechoMT novoTrecho = new TrechoMT();
            novoTrecho.setPosteOrigem(posteOrigem);
            // novoTrecho.setCodigo("TR-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
            novoTrecho.setPosteDestino(posteDestino);
            novoTrecho.setAlimentador(rede.getAlimentador());

            rede.getTrechosMT().add(novoTrecho);
            redeConectada.add(posteDestino);
        }

        rede.setPostesMT(redeConectada);
        rede.setPostesBT(postesDesconectados);
    }
}