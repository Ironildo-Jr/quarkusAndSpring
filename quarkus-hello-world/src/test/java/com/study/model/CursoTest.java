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

public class CursoTest {
    private static final Integer ID = 10;
    private static final String NOME = "nome";
    private static final String DESCRICAO = "Qualquer coisa";
    private static final Integer DURACAO = 100;
    private static final Professor PROFESSOR = Professor.builder().id(ID).nome(NOME).sexo(SEXO.MASCULINO)
            .titulo(TITULO.DOUTORADO).build();

    final static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final static Validator VALIDATOR = factory.getValidator();

    @Test
    void construtorBuilder() {
        // GIVEN

        // WHEN
        Curso curso = Curso.builder().id(ID).nome(NOME).descricao(DESCRICAO).duracao(DURACAO).professor(PROFESSOR).build();

        // THEN
        assertFields(VALIDATOR, curso);
    }

    @Test
    void construtorAllArgs() {
        // GIVEN

        // WHEN
        Curso curso = new Curso(ID, NOME, DESCRICAO, DURACAO, PROFESSOR);

        // THEN
        assertFields(VALIDATOR, curso);
    }

    @Test
    void construtorPadrao() {
        // GIVEN

        // WHEN
        Curso curso = new Curso();
        curso.setId(ID);
        curso.setNome(NOME);
        curso.setDescricao(DESCRICAO);
        curso.setDuracao(DURACAO);
        curso.setProfessor(PROFESSOR);

        // THEN
        assertFields(VALIDATOR, curso);
    }

    @ParameterizedTest
    @MethodSource("nomeInvalido")
    void construtor_SemNome(final String nome, final String errorMessage) {
        // GIVEN

        // WHEN
        Curso curso = new Curso();
        curso.setId(ID);
        curso.setNome(nome);
        curso.setDescricao(DESCRICAO);
        curso.setDuracao(DURACAO);
        curso.setProfessor(PROFESSOR);


        // THEN
        final Set<ConstraintViolation<Curso>> violations = VALIDATOR.validate(curso);
        assertThat(violations)
                .isNotEmpty()
                .hasSize(1);
        assertThat(violations.stream().findFirst().get().getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest
    @MethodSource("descricaoInvalida")
    void construtor_SemDescricao(final String descricao, final String errorMessage) {
        // GIVEN

        // WHEN
        Curso curso = new Curso();
        curso.setId(ID);
        curso.setNome(NOME);
        curso.setDescricao(descricao);
        curso.setDuracao(DURACAO);
        curso.setProfessor(PROFESSOR);

        // THEN
        final Set<ConstraintViolation<Curso>> violations = VALIDATOR.validate(curso);
        assertThat(violations)
                .isNotEmpty()
                .hasSize(1);
        assertThat(violations.stream().findFirst().get().getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest
    @MethodSource("duracaoInvalida")
    void construtor_SemDuracao(final Integer duracao, final String errorMessage) {
        // GIVEN

        // WHEN
        Curso curso = new Curso();
        curso.setId(ID);
        curso.setNome(NOME);
        curso.setDescricao(DESCRICAO);
        curso.setDuracao(duracao);
        curso.setProfessor(PROFESSOR);

        // THEN
        final Set<ConstraintViolation<Curso>> violations = VALIDATOR.validate(curso);
        assertThat(violations)
                .isNotEmpty()
                .hasSize(1);
        assertThat(violations.stream().findFirst().get().getMessage()).isEqualTo(errorMessage);
    }

    @Test
    void equalsAndHashcodeContract() {
        EqualsVerifier.simple().forClass(Curso.class)
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

    static Stream<Arguments> descricaoInvalida() {
        return Stream.of(
                arguments(null, "Descricao nao pode ser vazia ou nula"),
                arguments(" ", "Descricao nao pode ser vazia ou nula"));
    }

    static Stream<Arguments> duracaoInvalida() {
        return Stream.of(
                arguments(null, "Duracao nao pode ser vazia ou nula"));
    }

    private void assertFields(final Validator validator, final Curso curso) {
        final Set<ConstraintViolation<Curso>> violations = validator.validate(curso);
        assertThat(violations).isEmpty();

        assertThat(curso.getNome()).isEqualTo(NOME);
        assertThat(curso.getDescricao()).isEqualTo(DESCRICAO);
        assertThat(curso.getDuracao()).isEqualTo(DURACAO);
    }
}
