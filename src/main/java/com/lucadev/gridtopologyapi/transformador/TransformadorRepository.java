package com.lucadev.gridtopologyapi.transformador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransformadorRepository extends JpaRepository<Transformador, Long> {

    Optional<Transformador> findByCodigo(String codigo);

    List<Transformador> findByPosteId(Long posteId);
}
