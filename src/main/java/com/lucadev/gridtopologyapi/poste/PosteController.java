package com.lucadev.gridtopologyapi.poste;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/postes")
@RequiredArgsConstructor
public class PosteController {
    private final PosteService posteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody PosteDTO poste) {
        posteService.save(poste);
    }

    @GetMapping
    @RequestMapping("{idProjeto}")
    @ResponseStatus(HttpStatus.OK)
    public List<Poste> findAll(@PathVariable Long idProjeto){
        return posteService.findAll(idProjeto);
    }
}
