package com.lucadev.gridtopologyapi.contexto.service;

import com.lucadev.gridtopologyapi.contexto.ContextoRede;
import com.lucadev.gridtopologyapi.poste.PosteRepository;
import com.lucadev.gridtopologyapi.transformador.TransformadorRepository;
import com.lucadev.gridtopologyapi.trechobt.TrechoBTRepository;
import com.lucadev.gridtopologyapi.trechomt.TrechoMTRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersistenciaContexto implements Persistencia {

    private final PosteRepository posteRepository;
    private final TrechoMTRepository trechoMTRepository;
    private final TransformadorRepository transformadorRepository;
    private final TrechoBTRepository trechoBTRepository;

    @Transactional
    @Override
    public void salvarContexto(ContextoRede contexto) {

        // 1. Salva todos os postes de uma vez só (Bulk Insert)
        posteRepository.saveAll(contexto.getPostes());

        // 2. Salva todos os trechos de Média Tensão
        trechoMTRepository.saveAll(contexto.getTrechosMT());

        // 3. Salva os Transformadores
        transformadorRepository.saveAll(contexto.getTransformadores());

        // 4. Salva os trechos de Baixa Tensão
        trechoBTRepository.saveAll(contexto.getTrechosBT());

        // Nota: Assumindo que a Subestação e o Alimentador já foram
        // salvos antes ou estão sendo salvos em cascata.
    }

    @Override
    public ContextoRede instanciarContexto(Long idProjeto, Long idSubestacao, Long idAlimentador) {
        return null;
    }
}
