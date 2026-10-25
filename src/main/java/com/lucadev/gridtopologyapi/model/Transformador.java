package com.lucadev.gridtopologyapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(
    name = "transformador",
    indexes = {
        @Index(name = "idx_transformador_poste", columnList = "poste_id")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transformador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, unique = true, length = 40)
    private String codigo;

    @Column(name = "potencia_kva", precision = 10, scale = 2)
    private BigDecimal potenciaKva;

    @Column(name = "tensao_primaria_kv", precision = 8, scale = 3)
    private BigDecimal tensaoPrimariaKv;

    @Column(name = "tensao_secundaria_v", precision = 8, scale = 2)
    private BigDecimal tensaoSecundariaV;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "poste_id", nullable = false)
    private Poste poste;
}
