package com.lucadev.gridtopologyapi.contexto;

import com.lucadev.gridtopologyapi.alimentador.Alimentador;
import com.lucadev.gridtopologyapi.poste.Poste;
import com.lucadev.gridtopologyapi.projeto.Projeto;
import com.lucadev.gridtopologyapi.subestacao.Subestacao;
import com.lucadev.gridtopologyapi.transformador.Transformador;
import com.lucadev.gridtopologyapi.trechobt.TrechoBT;
import com.lucadev.gridtopologyapi.trechomt.TrechoMT;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContextoRede {
    private Projeto projeto;
    private Integer qntdPoste;

    private Subestacao subestacao;
    private Alimentador alimentador;
    @Builder.Default
    private List<Poste> postes = new ArrayList<>();
    @Builder.Default
    private List<Poste> postesBT = new ArrayList<>();
    @Builder.Default
    private List<Poste> postesMT = new ArrayList<>();
    @Builder.Default
    private List<TrechoMT> trechosMT = new ArrayList<>();
    @Builder.Default
    private List<TrechoBT> trechosBT = new ArrayList<>();
    @Builder.Default
    private List<Transformador> transformadores = new ArrayList<>();

}
