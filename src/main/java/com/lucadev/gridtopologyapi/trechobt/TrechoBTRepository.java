package com.lucadev.gridtopologyapi.trechobt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrechoBTRepository extends JpaRepository<TrechoBT, Long> {

    List<TrechoBT> findAllByPosteDestino_Projeto_Id(Long id);

    List<TrechoBT> findByTransformadorId(Long transformadorId);

    List<TrechoBT> findByPosteOrigemIdOrPosteDestinoId(Long posteOrigemId, Long posteDestinoId);
}
