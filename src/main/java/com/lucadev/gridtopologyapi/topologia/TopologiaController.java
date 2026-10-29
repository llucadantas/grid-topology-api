package com.lucadev.gridtopologyapi.topologia;

import com.lucadev.gridtopologyapi.topologia.grafo.TopologiaGrafoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/topologia")
public class TopologiaController {
    private final TopologiaService topologiaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TopologiaResponse criarRede(@RequestBody TopologiaDTO topologiaDTO) {
        return topologiaService.criarRedeAleatoria(topologiaDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/projeto/{idProjeto}/subestacao/{idSubestacao}/alimentador/{idAlimentador}")
    public TopologiaGrafoDTO buscarGrafo(@PathVariable Long idProjeto,  @PathVariable Long idSubestacao, @PathVariable Long idAlimentador) {
        return topologiaService.criarGrafo(idProjeto, idSubestacao, idAlimentador);
    }

}
