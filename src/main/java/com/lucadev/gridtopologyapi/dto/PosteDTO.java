package com.lucadev.gridtopologyapi.dto;

import com.lucadev.gridtopologyapi.model.Poste;

import java.math.BigDecimal;

public record PosteDTO(Long id, BigDecimal altura, String material, Long idProjeto){

    public PosteDTO(Poste poste) {
        this(poste.getId(), poste.getAlturaM(), poste.getMaterial(), poste.getProjeto().getId());
    }
}
