package com.lucadev.gridtopologyapi.topologia;

import com.lucadev.gridtopologyapi.alimentador.Alimentador;
import com.lucadev.gridtopologyapi.alimentador.AlimentadorMapper;
import com.lucadev.gridtopologyapi.alimentador.AlimentadorRepository;
import com.lucadev.gridtopologyapi.topologia.grafo.GrafoAdapter;
import com.lucadev.gridtopologyapi.topologia.grafo.TopologiaGrafoDTO;
import com.lucadev.gridtopologyapi.subestacao.Subestacao;
import com.lucadev.gridtopologyapi.subestacao.SubestacaoMapper;
import com.lucadev.gridtopologyapi.subestacao.SubestacaoRepository;
import com.lucadev.gridtopologyapi.topologia.strategy.GerarRede;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopologiaService {
    private final AlimentadorMapper  alimentadorMapper;
    private final SubestacaoMapper subestacaoMapper;
    private final Persistencia persistencia;
    private final List<GerarRede> gerarRedes;
    private final AlimentadorRepository alimentadorRepository;
    private final SubestacaoRepository subestacaoRepository;
    private final TopologiaMapper topologiaMapper;
    private final GrafoAdapter grafoAdapter;

    public TopologiaResponse criarRedeAleatoria(TopologiaDTO contextoDto) {

        Subestacao subestacao = subestacaoRepository.save(subestacaoMapper.toSubestacao(contextoDto.subestacao()));
        Alimentador alimentador = alimentadorRepository.save(alimentadorMapper.toAlimentador(contextoDto.alimentador(), subestacao));

        Topologia topologia = Topologia.builder()
                .alimentador(alimentador)
                .subestacao(subestacao)
                .projeto(subestacao.getProjeto())
                .qntdPoste(contextoDto.qntdPostePorAlimentador())
                .build();
        for(GerarRede gerarRede : gerarRedes){
            gerarRede.gerarContexto(topologia);
        }
        persistencia.salvarContexto(topologia);
        return topologiaMapper.mapTopologiaDTO(topologia);
    }

    public TopologiaGrafoDTO criarGrafo(Long idProjeto, Long idSubestacao, Long idAlimentador) {
        Topologia topologia = persistencia.instanciarContexto(idProjeto, idSubestacao, idAlimentador);
        TopologiaGrafoDTO topDto = grafoAdapter.converterParaGrafo(topologia);
        return topDto;
    }
}
