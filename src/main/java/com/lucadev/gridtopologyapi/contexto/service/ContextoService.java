package com.lucadev.gridtopologyapi.contexto.service;

import com.lucadev.gridtopologyapi.contexto.ContextoAutomatizadoDTO;
import com.lucadev.gridtopologyapi.contexto.ContextoRede;
import com.lucadev.gridtopologyapi.contexto.strategy.GerarRede;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContextoService {
    private final Persistencia persistencia;
    private final List<GerarRede> gerarRedes;

    public ContextoRede criarRedeAleatoria(ContextoAutomatizadoDTO contextoDto) {
        ContextoRede contextoRede = ContextoRede.builder()
                .projeto(contextoDto.)
                .build();

        for(GerarRede gerarRede : gerarRedes){

        }
    }
}
