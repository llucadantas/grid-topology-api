package com.lucadev.gridtopologyapi.transformador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransformadorRepository extends JpaRepository<Transformador, Long> {

    List<Transformador> findAllByPoste_Projeto_Id(Long id);
    List<Transformador> findByPosteId(Long posteId);
}
