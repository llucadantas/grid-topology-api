package com.lucadev.gridtopologyapi.contexto;

import com.lucadev.gridtopologyapi.alimentador.Alimentador;
import com.lucadev.gridtopologyapi.subestacao.Subestacao;

public record ContextoAutomatizadoDTO(Subestacao subestacao, Alimentador alimentador, Integer qntdPostePorAlimentador) {
}
