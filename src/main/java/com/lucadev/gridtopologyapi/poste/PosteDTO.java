package com.lucadev.gridtopologyapi.poste;

import java.math.BigDecimal;

public record PosteDTO(Long id, Long idProjeto){

    public PosteDTO(Poste poste) {
        this(poste.getId(),poste.getProjeto().getId());
    }
}
