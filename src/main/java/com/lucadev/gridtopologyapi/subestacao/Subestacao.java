package com.lucadev.gridtopologyapi.subestacao;

import com.lucadev.gridtopologyapi.projeto.Projeto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "subestacao",
    indexes = {
        @Index(name = "idx_subestacao_projeto", columnList = "projeto_id")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Subestacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;
}
