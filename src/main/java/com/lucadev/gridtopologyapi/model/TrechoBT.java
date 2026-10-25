package com.lucadev.gridtopologyapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "trecho_bt",
    indexes = {
        @Index(name = "idx_trecho_bt_transformador", columnList = "transformador_id"),
        @Index(name = "idx_trecho_bt_origem", columnList = "poste_origem_id"),
        @Index(name = "idx_trecho_bt_destino", columnList = "poste_destino_id")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TrechoBT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, unique = true, length = 40)
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transformador_id", nullable = false)
    private Transformador transformador;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "poste_origem_id", nullable = false)
    private Poste posteOrigem;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "poste_destino_id", nullable = false)
    private Poste posteDestino;
}
