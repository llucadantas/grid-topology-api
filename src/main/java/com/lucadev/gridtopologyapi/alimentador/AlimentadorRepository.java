package com.lucadev.gridtopologyapi.alimentador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlimentadorRepository extends JpaRepository<Alimentador, Long> {

    List<Alimentador> findBySubestacaoId(Long subestacaoId);

    Optional<Alimentador> findBySubestacaoIdAndCodigo(Long subestacaoId, String codigo);

    boolean existsBySubestacao_IdAndSubestacao_Projeto_IdAndId(Long subestacaoId, Long subestacaoProjetoId, Long id);
}
