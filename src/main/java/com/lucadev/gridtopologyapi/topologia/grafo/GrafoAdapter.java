package com.lucadev.gridtopologyapi.topologia.grafo;

import com.lucadev.gridtopologyapi.poste.Poste;
import com.lucadev.gridtopologyapi.topologia.Topologia;
import com.lucadev.gridtopologyapi.transformador.Transformador;
import com.lucadev.gridtopologyapi.trechobt.TrechoBT;
import com.lucadev.gridtopologyapi.trechomt.TrechoMT;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GrafoAdapter {

    public TopologiaGrafoDTO converterParaGrafo(Topologia rede) {
        TopologiaGrafoDTO grafo = new TopologiaGrafoDTO();
        List<Poste> postes = rede.getPostes();

        // 1. Transforma todos os POSTES em NÓS (Nodes)
        for (Poste poste : postes) { // Aqui você uniria postes MT e BT se existirem separados
            NodeDTO node = new NodeDTO();
            node.setId("P-" + poste.getId()); // Ex: P-10

            // Lógica para colorir/agrupar o nó na imagem
            if (temTransformador(poste, rede)) {
                node.setGroup("TRANSFORMADOR");
            } else {
                node.setGroup("POSTE_COMUM");
            }
            grafo.getNodes().add(node);
        }

        // 2. Transforma os TRECHOS em ARESTAS (Edges)
        for (TrechoBT trecho : rede.getTrechosBT()) {
            EdgeDTO edge = new EdgeDTO();
            edge.setId("BT-" + trecho.getId());
            edge.setSource("P-" + trecho.getPosteOrigem().getId());
            edge.setTarget("P-" + trecho.getPosteDestino().getId());
            edge.setType("BAIXA_TENSAO");
            grafo.getEdges().add(edge);
        }

        for (TrechoMT trecho : rede.getTrechosMT()) {
            EdgeDTO edge = new EdgeDTO();
            edge.setId("MT-" + trecho.getId());
            edge.setSource("P-" + trecho.getPosteOrigem().getId());
            edge.setTarget("P-" + trecho.getPosteDestino().getId());
            edge.setType("MEDIA_TENSAO");
            grafo.getEdges().add(edge);
        }

        return grafo;
    }

    private boolean temTransformador(Poste poste, Topologia rede) {
        for(Transformador transformador : rede.getTransformadores()) {
            if(transformador.getPoste().getId().equals(poste.getId())) {
                return true;
            }
        }
        return false;
    }
}