package com.study.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.Id;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.study.enums.SEXO;
import com.study.enums.TITULO;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ProfessorTest {
    private static final String NOME = "nome";
    private static final Integer ID = 10;
    private static final SEXO SEXOA = SEXO.MASCULINO;
    private static final TITULO TITULOA = TITULO.DOUTORADO;
    private static final Curso CURSO = Curso.builder().id(ID).nome(NOME).descricao(" Qualquer coisa").duracao(100)
            .professor(new Professor()).build();
    private static final List<Aluno> ALUNOS = List.of(Aluno.builder().id(ID).nome(NOME).sexo(SEXOA)
            .matricula("F0000000").professor(new Professor()).build(),
            Aluno.builder().id(ID).nome(NOME).sexo(SEXOA)
                    .matricula("F0000000").professor(new Professor()).build());

    final static ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
    final static Validator VALIDATOR = FACTORY.getValidator();

    @Test
    void construtorBuilder() {
        // GIVEN

        // WHEN
        Professor professor = Professor.builder().id(ID).nome(NOME).sexo(SEXOA).titulo(TITULOA).curso(CURSO)
                .alunos(ALUNOS).build();

        // THEN
        assertFields(VALIDATOR, professor);
    }

    @Test
    void construtorAllArgs() {
        // GIVEN

        // WHEN
        Professor professor = new Professor(ID, NOME, SEXOA, TITULOA, CURSO, ALUNOS);

        // THEN
        assertFields(VALIDATOR, professor);
    }

    @Test
    void construtorPadrao() {
        // GIVEN

        // WHEN
        Professor professor = new Professor();
        professor.setId(ID);
        professor.setNome(NOME);
        professor.setSexo(SEXOA);
        professor.setTitulo(TITULOA);
        professor.setCurso(CURSO);
        professor.setAlunos(ALUNOS);

        // THEN
        assertFields(VALIDATOR, professor);
    }

    @ParameterizedTest
    @MethodSource("nomeInvalido")
    void construtor_SemNome(final String nome, final String errorMessage) {
        // GIVEN

        // WHEN
        Professor professor = new Professor();
        professor.setId(ID);
        professor.setNome(nome);
        professor.setSexo(SEXOA);
        professor.setTitulo(TITULOA);
        professor.setCurso(CURSO);
        professor.setAlunos(ALUNOS);

        // THEN
        final Set<ConstraintViolation<Professor>> violations = VALIDATOR.validate(professor);
        assertThat(violations)
                .isNotEmpty()
                .hasSize(1);
        assertThat(violations.stream().findFirst().get().getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest
    @MethodSource("TituloInvalido")
    void construtor_SemTitulo(final TITULO titulo, final String errorMessage) {
        // GIVEN

        // WHEN
        Professor professor = new Professor();
        professor.setId(ID);
        professor.setNome(NOME);
        professor.setSexo(SEXOA);
        professor.setTitulo(titulo);
        professor.setCurso(CURSO);
        professor.setAlunos(ALUNOS);

        // THEN
        final Set<ConstraintViolation<Professor>> violations = VALIDATOR.validate(professor);
        assertThat(violations)
                .isNotEmpty()
                .hasSize(1);
        assertThat(violations.stream().findFirst().get().getMessage()).isEqualTo(errorMessage);
    }

    @Test
    void equalsAndHashcodeContract() {
        EqualsVerifier.simple().forClass(Professor.class)
                .withIgnoredAnnotations(Id.class)
                .withPrefabValues(Professor.class,
                        Professor.builder().nome("prof1").titulo(TITULO.DOUTORADO).build(), new Professor())
                .verify();
    }

    static Stream<Arguments> nomeInvalido() {
        return Stream.of(
                arguments(null, "Nome nao pode ser vazio ou nulo"),
                arguments(" ", "Nome nao pode ser vazio ou nulo"));
    }

    static Stream<Arguments> TituloInvalido() {
        return Stream.of(arguments(null, "Titulo nao pode ser vazio ou nulo"));
    }

    private void assertFields(final Validator validator, final Professor professor) {
        final Set<ConstraintViolation<Professor>> violations = validator.validate(professor);
        assertThat(violations).isEmpty();

        assertThat(professor.getNome()).isEqualTo(NOME);
        assertThat(professor.getSexo()).isEqualTo(SEXOA);
        assertThat(professor.getTitulo()).isEqualTo(TITULOA);
    }
}
