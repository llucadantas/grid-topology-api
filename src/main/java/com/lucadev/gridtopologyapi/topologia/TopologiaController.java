package com.lucadev.gridtopologyapi.topologia;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/topologia")
public class TopologiaController {
    private final TopologiaOrquestrador topologiaOrquestrador;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TopologiaResponse criarRede(@RequestBody TopologiaDTO topologiaDTO) {
        return topologiaOrquestrador.criarRedeAleatoria(topologiaDTO);
    }

}
