package com.lucadev.gridtopologyapi.projeto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjetoService {
    private final ProjetoRepository projetoRepository;

    @Transactional
    public void salvarProjeto(ProjetoDTO projetoDTO) {
        Projeto projeto = new Projeto();
        projeto.setNome(projetoDTO.nome());
        projeto.setDescricao(projetoDTO.descricao());
        projetoRepository.save(projeto);
    }

    public Projeto findById(Long idProjeto) {
        return projetoRepository.findById(idProjeto).orElseThrow();
    }
}
