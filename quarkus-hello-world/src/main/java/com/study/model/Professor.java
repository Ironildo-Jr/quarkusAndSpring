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
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "Sexo")
    private SEXO sexo;

    @Enumerated(EnumType.STRING)
    @Column(name = "Titulo", nullable = false)
    private TITULO titulo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Curso")
    private Curso curso;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "professor")
    private List<Aluno> alunos;
}
