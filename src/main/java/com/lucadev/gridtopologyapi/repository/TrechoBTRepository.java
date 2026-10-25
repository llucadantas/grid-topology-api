package com.lucadev.gridtopologyapi.repository;

import com.lucadev.gridtopologyapi.model.TrechoBT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrechoBTRepository extends JpaRepository<TrechoBT, Long> {

    Optional<TrechoBT> findByCodigo(String codigo);

    List<TrechoBT> findByTransformadorId(Long transformadorId);

    List<TrechoBT> findByPosteOrigemIdOrPosteDestinoId(Long posteOrigemId, Long posteDestinoId);
}
