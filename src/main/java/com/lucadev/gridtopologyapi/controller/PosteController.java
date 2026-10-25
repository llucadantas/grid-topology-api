package com.lucadev.gridtopologyapi.controller;

import com.lucadev.gridtopologyapi.dto.PosteDTO;
import com.lucadev.gridtopologyapi.model.Poste;
import com.lucadev.gridtopologyapi.service.PosteService;
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
    public List<Poste> findAll(@PathVariable Long idProjeto){
        return posteService.findAll(idProjeto);
    }
}
