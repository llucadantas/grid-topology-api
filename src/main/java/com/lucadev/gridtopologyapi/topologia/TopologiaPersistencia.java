package com.lucadev.gridtopologyapi.topologia;

import com.lucadev.gridtopologyapi.alimentador.Alimentador;
import com.lucadev.gridtopologyapi.alimentador.AlimentadorRepository;
import com.lucadev.gridtopologyapi.poste.PosteRepository;
import com.lucadev.gridtopologyapi.subestacao.Subestacao;
import com.lucadev.gridtopologyapi.subestacao.SubestacaoRepository;
import com.lucadev.gridtopologyapi.transformador.TransformadorRepository;
import com.lucadev.gridtopologyapi.trechobt.TrechoBTRepository;
import com.lucadev.gridtopologyapi.trechomt.TrechoMTRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TopologiaPersistencia implements Persistencia {

    private final PosteRepository posteRepository;
    private final TrechoMTRepository trechoMTRepository;
    private final TransformadorRepository transformadorRepository;
    private final TrechoBTRepository trechoBTRepository;
    private final AlimentadorRepository alimentadorRepository;
    private final SubestacaoRepository subestacaoRepository;

    @Transactional
    @Override
    public void salvarContexto(Topologia contexto) {

        // 1. Salva todos os postes de uma vez só (Bulk Insert)
        posteRepository.saveAll(contexto.getPostes());

        // 2. Salva todos os trechos de Média Tensão
        trechoMTRepository.saveAll(contexto.getTrechosMT());

        // 3. Salva os Transformadores
        transformadorRepository.saveAll(contexto.getTransformadores());

        // 4. Salva os trechos de Baixa Tensão
        trechoBTRepository.saveAll(contexto.getTrechosBT());


    }

    @Override
    public Topologia instanciarContexto(Long idProjeto, Long idSubestacao, Long idAlimentador) {
        return null;
    }
}
