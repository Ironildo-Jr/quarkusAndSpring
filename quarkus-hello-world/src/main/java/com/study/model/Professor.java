package com.study.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.study.enums.SEXO;
import com.study.enums.TITULO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROFESSOR")
public class Professor {
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

    @NotNull(message = "Titulo nao pode ser vazio ou nulo")
    @Enumerated(EnumType.STRING)
    @Column(name = "Titulo", nullable = false)
    private TITULO titulo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Curso")
    private Curso curso;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "professor")
    private List<Aluno> alunos;
}
