package com.lucadev.gridtopologyapi.trechomt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrechoMTRepository extends JpaRepository<TrechoMT, Long> {

    List<TrechoMT> findByAlimentadorId(Long alimentadorId);

    List<TrechoMT> findByPosteOrigemIdOrPosteDestinoId(Long posteOrigemId, Long posteDestinoId);
}
