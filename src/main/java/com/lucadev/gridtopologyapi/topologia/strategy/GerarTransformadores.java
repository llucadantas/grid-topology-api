package com.lucadev.gridtopologyapi.topologia.strategy;

import com.lucadev.gridtopologyapi.topologia.Topologia;
import com.lucadev.gridtopologyapi.poste.Poste;
import com.lucadev.gridtopologyapi.transformador.Transformador;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
@Order(3)
public class GerarTransformadores implements GerarRede {

    @Override
    public void gerarContexto(Topologia rede) {
        Random random = new Random();
        gerarTransformadoresRecursivo(rede, 0, random, new ArrayList<>(rede.getPostesMT()));
    }

    public void gerarTransformadoresRecursivo(Topologia rede, int qntdTransformadores, Random random, List<Poste> postesDisponiveis) {
        if(qntdTransformadores == 0) qntdTransformadores = (int) (rede.getPostes().size() * 0.1);
        if(qntdTransformadores <= rede.getTransformadores().size()) return;

        Poste poste = postesDisponiveis.remove(random.nextInt(postesDisponiveis.size()));
        Transformador tx = new Transformador();
        tx.setPoste(poste);
        rede.getTransformadores().add(tx);
        gerarTransformadoresRecursivo(rede, qntdTransformadores, random, postesDisponiveis);
    }
}