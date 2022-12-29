package com.study.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CURSO")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @NotBlank(message = "Nome nao pode ser vazio ou nulo")
    @Column(name = "Nome", nullable = false)
    private String nome;

    @NotBlank(message = "Descricao nao pode ser vazia ou nula")
    @Column(name = "Descricao", nullable = false)
    private String descricao;

    @NotNull(message = "Duracao nao pode ser vazia ou nula")
    @Column(name = "Duracao", nullable = false)
    private Integer duracao;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "curso")
    private Professor professor;
}
