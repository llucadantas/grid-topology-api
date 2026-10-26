package com.lucadev.gridtopologyapi.poste;

import com.lucadev.gridtopologyapi.projeto.ProjetoRepository;
import com.lucadev.gridtopologyapi.projeto.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PosteService {
    private final PosteRepository posteRepository;
    private final ProjetoService projetoService;

    @Transactional
    public Poste save(PosteDTO posteDTO) {
        Poste poste = new Poste();
        poste.setProjeto(projetoService.findById(posteDTO.idProjeto()));
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
