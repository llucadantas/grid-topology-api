package com.lucadev.gridtopologyapi.repository;

import com.lucadev.gridtopologyapi.model.TrechoMT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrechoMTRepository extends JpaRepository<TrechoMT, Long> {

    Optional<TrechoMT> findByCodigo(String codigo);

    List<TrechoMT> findByAlimentadorId(Long alimentadorId);

    List<TrechoMT> findByPosteOrigemIdOrPosteDestinoId(Long posteOrigemId, Long posteDestinoId);
}
