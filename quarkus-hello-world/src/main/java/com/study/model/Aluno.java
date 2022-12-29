package com.study.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.study.enums.SEXO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ALUNO")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @NotBlank(message = "Nome nao pode ser vazio ou nulo")
    @Column(name = "Nome", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "Sexo")
    private SEXO sexo;

    @NotBlank(message = "Matricula nao pode ser vazia ou nula")
    @Column(name = "Matricula", nullable = false)
    private String matricula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Professor")
    private Professor professor;
}
