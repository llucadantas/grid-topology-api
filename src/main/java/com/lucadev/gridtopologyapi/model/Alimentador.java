package com.lucadev.gridtopologyapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(
    name = "alimentador",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_alimentador_subestacao_codigo", columnNames = {"subestacao_id", "codigo"})
    },
    indexes = {
        @Index(name = "idx_alimentador_subestacao", columnList = "subestacao_id")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Alimentador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 30)
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subestacao_id", nullable = false)
    private Subestacao subestacao;

    @Column(name = "tensao_nominal_kv", precision = 8, scale = 3)
    private BigDecimal tensaoNominalKv;
}
