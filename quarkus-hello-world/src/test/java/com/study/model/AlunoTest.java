package com.study.model;

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

import java.util.Set;
import java.util.stream.Stream;

public class AlunoTest {
    private static final String NOME = "nome";
    private static final Integer ID = 10;
    private static final SEXO SEXOA = SEXO.MASCULINO;
    private static final String MATRICULA = "F0000000";
    private static final Professor PROFESSOR = Professor.builder().id(ID).nome(NOME).sexo(SEXOA)
            .titulo(TITULO.DOUTORADO).build();

    final static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final static Validator VALIDATOR = factory.getValidator();

    @Test
    void construtorBuilder() {
        // GIVEN

        // WHEN
        Aluno aluno = Aluno.builder().id(ID).nome(NOME).sexo(SEXOA).matricula(MATRICULA).professor(PROFESSOR).build();

        // THEN
        assertFields(VALIDATOR, aluno);
    }

    @Test
    void construtorAllArgs() {
        // GIVEN

        // WHEN
        Aluno aluno = new Aluno(ID, NOME, SEXOA, MATRICULA, PROFESSOR);

        // THEN
        assertFields(VALIDATOR, aluno);
    }

    @Test
    void construtorPadrao() {
        // GIVEN

        // WHEN
        Aluno aluno = new Aluno();
        aluno.setId(ID);
        aluno.setNome(NOME);
        aluno.setSexo(SEXOA);
        aluno.setMatricula(MATRICULA);
        aluno.setProfessor(PROFESSOR);

        // THEN
        assertFields(VALIDATOR, aluno);
    }

    @ParameterizedTest
    @MethodSource("nomeInvalido")
    void construtor_SemNome(final String nome, final String errorMessage) {
        // GIVEN

        // WHEN
        Aluno aluno = new Aluno();
        aluno.setId(ID);
        aluno.setNome(nome);
        aluno.setSexo(SEXOA);
        aluno.setMatricula(MATRICULA);
        aluno.setProfessor(PROFESSOR);

        // THEN
        final Set<ConstraintViolation<Aluno>> violations = VALIDATOR.validate(aluno);
        assertThat(violations)
                .isNotEmpty()
                .hasSize(1);
        assertThat(violations.stream().findFirst().get().getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest
    @MethodSource("matriculaInvalida")
    void construtor_SemMatricula(final String matricula, final String errorMessage) {
        // GIVEN

        // WHEN
        Aluno aluno = new Aluno();
        aluno.setId(ID);
        aluno.setNome(NOME);
        aluno.setSexo(SEXOA);
        aluno.setMatricula(matricula);
        aluno.setProfessor(PROFESSOR);

        // THEN
        final Set<ConstraintViolation<Aluno>> violations = VALIDATOR.validate(aluno);
        assertThat(violations)
                .isNotEmpty()
                .hasSize(1);
        assertThat(violations.stream().findFirst().get().getMessage()).isEqualTo(errorMessage);
    }

    @Test
    void equalsAndHashcodeContract() {
        EqualsVerifier.simple().forClass(Aluno.class)
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

    static Stream<Arguments> matriculaInvalida() {
        return Stream.of(
                arguments(null, "Matricula nao pode ser vazia ou nula"),
                arguments(" ", "Matricula nao pode ser vazia ou nula"));
    }


    private void assertFields(final Validator validator, final Aluno aluno) {
        final Set<ConstraintViolation<Aluno>> violations = validator.validate(aluno);
        assertThat(violations).isEmpty();

        assertThat(aluno.getNome()).isEqualTo(NOME);
        assertThat(aluno.getSexo()).isEqualTo(SEXOA);
        assertThat(aluno.getMatricula()).isEqualTo(MATRICULA);
    }
}
