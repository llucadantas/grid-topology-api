package com.lucadev.gridtopologyapi.topologia.strategy;

import com.lucadev.gridtopologyapi.topologia.Topologia;
import com.lucadev.gridtopologyapi.poste.Poste;
import com.lucadev.gridtopologyapi.trechomt.TrechoMT;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
@Order(2)
public class GerarRedeMT implements GerarRede {
    @Override
    public void gerarContexto(Topologia rede) {
        Random random = new Random();
        gerarMTRecursiva(rede, gerarLimite(), new ArrayList<>(), random, 0);
    }

    public double gerarLimite(){
        int x = 30;
        int y = 50;
        Random random = new Random();
        int numero = random.nextInt((y - x) + 1) + x;
        return (double) numero /100;
    }

    public void gerarMTRecursiva(Topologia rede, double limite, List<Poste> postesDisponiveis, Random random, int qntdPoste) {
        if (postesDisponiveis == null || postesDisponiveis.isEmpty()) {
            postesDisponiveis = new ArrayList<>(rede.getPostes());
            qntdPoste = (int) (postesDisponiveis.size() * limite);
        }

        List<Poste> redeConectada = rede.getPostesMT();

        if (redeConectada.isEmpty() && !postesDisponiveis.isEmpty()) {
            redeConectada.add(postesDisponiveis.removeFirst());
        }

        if (redeConectada.size() >= qntdPoste || postesDisponiveis.isEmpty()) return;

        Poste posteOrigem = redeConectada.get(random.nextInt(redeConectada.size()));
        Poste posteDestino = postesDisponiveis.remove(random.nextInt(postesDisponiveis.size()));

        TrechoMT novoTrecho = new TrechoMT();
        novoTrecho.setPosteOrigem(posteOrigem);
        novoTrecho.setPosteDestino(posteDestino);
        novoTrecho.setAlimentador(rede.getAlimentador());

        rede.getTrechosMT().add(novoTrecho);

        // Apenas adiciona o novo poste diretamente na rede tratada
        redeConectada.add(posteDestino);
        gerarMTRecursiva(rede, limite, postesDisponiveis, random, qntdPoste);
    }
}