package com.lucadev.gridtopologyapi.poste;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosteRepository extends JpaRepository<Poste, Long> {

    List<Poste> findByProjetoId(Long projetoId);
}
