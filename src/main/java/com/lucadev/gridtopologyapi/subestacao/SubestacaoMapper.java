package com.lucadev.gridtopologyapi.subestacao;

import com.lucadev.gridtopologyapi.projeto.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubestacaoMapper {
    private final ProjetoService projetoService;

    public Subestacao toSubestacao(SubestacaoDTO subestacao) {
        return Subestacao.builder()
                .nome(subestacao.nome())
                .projeto(projetoService.findById(subestacao.idProjeto()))
                .build();
    }
}
