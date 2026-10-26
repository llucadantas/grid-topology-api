package com.lucadev.gridtopologyapi.topologia;

import com.lucadev.gridtopologyapi.alimentador.AlimentadorDTO;
import com.lucadev.gridtopologyapi.subestacao.SubestacaoDTO;

public record TopologiaDTO(SubestacaoDTO subestacao, AlimentadorDTO alimentador, Integer qntdPostePorAlimentador) {
}

