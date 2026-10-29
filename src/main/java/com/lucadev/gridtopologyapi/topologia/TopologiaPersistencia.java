package com.lucadev.gridtopologyapi.topologia;

import com.lucadev.gridtopologyapi.alimentador.Alimentador;
import com.lucadev.gridtopologyapi.alimentador.AlimentadorRepository;
import com.lucadev.gridtopologyapi.poste.Poste;
import com.lucadev.gridtopologyapi.poste.PosteRepository;
import com.lucadev.gridtopologyapi.subestacao.Subestacao;
import com.lucadev.gridtopologyapi.subestacao.SubestacaoRepository;
import com.lucadev.gridtopologyapi.transformador.Transformador;
import com.lucadev.gridtopologyapi.transformador.TransformadorRepository;
import com.lucadev.gridtopologyapi.trechobt.TrechoBT;
import com.lucadev.gridtopologyapi.trechobt.TrechoBTRepository;
import com.lucadev.gridtopologyapi.trechomt.TrechoMT;
import com.lucadev.gridtopologyapi.trechomt.TrechoMTRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopologiaPersistencia implements Persistencia {

    private final PosteRepository posteRepository;
    private final TrechoMTRepository trechoMTRepository;
    private final TransformadorRepository transformadorRepository;
    private final TrechoBTRepository trechoBTRepository;
    private final AlimentadorRepository alimentadorRepository;

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
        // 1. Validação de Segurança (Opcional, mas altamente recomendada)
        // Lança exceção se o alimentador não for da subestação ou a SE não for do projeto
        validarHierarquia(idProjeto, idSubestacao, idAlimentador);

        // 2. Buscas isoladas rápidas (Resolve o problema do N+1)

        // Traz apenas os trechos MT pertencentes a este alimentador específico
        List<TrechoMT> trechosMT = trechoMTRepository.findByAlimentadorId(idAlimentador);

        // Como os postes estão atrelados ao projeto, trazemos os postes do projeto
        List<Poste> postesDoProjeto = posteRepository.findByProjetoId(idProjeto);

        // Se precisar dos transformadores e BT, você pode buscar usando o projeto_id
        // ou criar uma @Query para buscar apenas os vinculados a estes postes
        List<Transformador> transformadores = transformadorRepository.findAllByPoste_Projeto_Id(idProjeto);
        List<TrechoBT> trechosBT = trechoBTRepository.findAllByPosteDestino_Projeto_Id(idProjeto);

        // 3. Monta o objeto de domínio
        Topologia topologia = new Topologia();
        topologia.setTrechosMT(trechosMT);
        topologia.setPostes(postesDoProjeto);
        topologia.setTransformadores(transformadores);
        topologia.setTrechosBT(trechosBT);
        return topologia;
    }

    private void validarHierarquia(Long idProjeto, Long idSubestacao, Long idAlimentador){
        if(!alimentadorRepository.existsBySubestacao_IdAndSubestacao_Projeto_IdAndId(idProjeto, idSubestacao, idAlimentador)){
            throw new RuntimeException("Projeto invalido");
        }

    }
}
