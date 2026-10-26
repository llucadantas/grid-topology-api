package com.lucadev.gridtopologyapi.alimentador;

import com.lucadev.gridtopologyapi.subestacao.Subestacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlimentadorMapper {

    public Alimentador toAlimentador(AlimentadorDTO alimentadorDTO, Subestacao subestacao) {
        return Alimentador.builder()
                .codigo(alimentadorDTO.codigo())
                .subestacao(subestacao)
                .build();
    }
}
