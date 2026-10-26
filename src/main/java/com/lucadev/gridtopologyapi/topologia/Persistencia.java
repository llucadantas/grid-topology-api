package com.lucadev.gridtopologyapi.topologia;

public interface Persistencia {
    void salvarContexto(Topologia contexto);

    Topologia instanciarContexto(Long idProjeto, Long idSubestacao, Long idAlimentador);
}
