package com.lucadev.gridtopologyapi.topologia;

import org.springframework.stereotype.Component;

@Component
public class TopologiaMapper {

    public TopologiaResponse mapTopologiaDTO(Topologia topologia) {
        return new TopologiaResponse(topologia.getSubestacao().getNome(),
                topologia.getAlimentador().getCodigo(),
                topologia.getQntdPoste(),
                topologia.getTrechosMT().size(),
                topologia.getTrechosBT().size(),
                topologia.getTransformadores().size());
    }
}
