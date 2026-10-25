package com.lucadev.gridtopologyapi.service;

import com.lucadev.gridtopologyapi.dto.PosteDTO;
import com.lucadev.gridtopologyapi.model.Poste;
import com.lucadev.gridtopologyapi.repository.PosteRepository;
import com.lucadev.gridtopologyapi.repository.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PosteService {
    private final PosteRepository posteRepository;
    private final ProjetoRepository projetoRepository;

    @Transactional
    public Poste save(PosteDTO posteDTO) {
        Poste poste = new Poste();
        poste.setAlturaM(posteDTO.altura());
        poste.setMaterial(posteDTO.material());
        poste.setProjeto(projetoRepository.findById(posteDTO.idProjeto()).orElseThrow());
        return posteRepository.save(poste);
    }

    @Transactional
    public void delete(Long id) {
        posteRepository.deleteById(id);
    }

    public List<Poste> findAll(Long idProjeto) {
        return posteRepository.findByProjetoId(idProjeto);
    }
}
