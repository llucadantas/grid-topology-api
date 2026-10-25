package com.lucadev.gridtopologyapi.subestacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubestacaoRepository extends JpaRepository<Subestacao, Long> {

    List<Subestacao> findByProjetoId(Long projetoId);
}
