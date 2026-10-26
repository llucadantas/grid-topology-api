package com.lucadev.gridtopologyapi.contexto.service;

import com.lucadev.gridtopologyapi.contexto.ContextoRede;

public interface Persistencia {
    void salvarContexto(ContextoRede contexto);

    ContextoRede instanciarContexto(Long idProjeto, Long idSubestacao, Long idAlimentador);
}
