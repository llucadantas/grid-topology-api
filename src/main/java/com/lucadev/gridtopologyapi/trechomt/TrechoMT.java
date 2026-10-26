package com.lucadev.gridtopologyapi.trechomt;

import com.lucadev.gridtopologyapi.alimentador.Alimentador;
import com.lucadev.gridtopologyapi.poste.Poste;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "trecho_mt",
    indexes = {
        @Index(name = "idx_trecho_mt_alimentador", columnList = "alimentador_id"),
        @Index(name = "idx_trecho_mt_origem", columnList = "poste_origem_id"),
        @Index(name = "idx_trecho_mt_destino", columnList = "poste_destino_id")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TrechoMT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "alimentador_id", nullable = false)
    private Alimentador alimentador;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "poste_origem_id", nullable = false)
    private Poste posteOrigem;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "poste_destino_id", nullable = false)
    private Poste posteDestino;
}
