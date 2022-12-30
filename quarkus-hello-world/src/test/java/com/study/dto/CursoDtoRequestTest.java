package com.study.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Set;
import java.util.stream.Stream;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CursoDtoRequestTest {
    private static final String NOME = "nome";
    private static final String DESCRICAO = "Qualquer coisa";
    private static final Integer DURACAO = 100;

    private final static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final static Validator VALIDATOR = factory.getValidator();

    @AfterAll
    static void fechar() {
        factory.close();
    }

    @Test
    void construtorBuilder() {
        // GIVEN

        // WHEN
        CursoDtoRequest cursoRequest = CursoDtoRequest.builder().nome(NOME).descricao(DESCRICAO).duracao(DURACAO)
                .build();

        // THEN
        assertFields(cursoRequest);
    }

    @Test
    void construtorAllArgs() {
        // GIVEN

        // WHEN
        CursoDtoRequest cursoRequest = new CursoDtoRequest(NOME, DESCRICAO, DURACAO);

        // THEN
        assertFields(cursoRequest);
    }

    @Test
    void construtorPadrao() {
        // GIVEN

        // WHEN
        CursoDtoRequest cursoRequest = new CursoDtoRequest();
        cursoRequest.setNome(NOME);
        cursoRequest.setDescricao(DESCRICAO);
        cursoRequest.setDuracao(DURACAO);

        // THEN
        assertFields(cursoRequest);
    }

    @ParameterizedTest
    @MethodSource("CamposInvalidos")
    void construtor_CamposInvalidos(final String nome, final String descricao, final Integer duracao,
            final String errorMessage) {
        // GIVEN

        // WHEN
        CursoDtoRequest cursoRequest = new CursoDtoRequest();
        cursoRequest.setNome(nome);
        cursoRequest.setDescricao(descricao);
        cursoRequest.setDuracao(duracao);

        // THEN
        final Set<ConstraintViolation<CursoDtoRequest>> violations = VALIDATOR.validate(cursoRequest);
        assertThat(violations)
                .isNotEmpty()
                .hasSize(1);
        assertThat(violations.stream().findFirst().get().getMessage()).isEqualTo(errorMessage);
    }

    @Test
    void equalsAndHashcodeContract() {
        EqualsVerifier.simple().forClass(CursoDtoRequest.class)
                .verify();
    }

    static Stream<Arguments> CamposInvalidos() {
        return Stream.of(
                arguments(null, DESCRICAO, DURACAO, "Nome nao pode ser nulo ou vazio"),
                arguments("  ", DESCRICAO, DURACAO, "Nome nao pode ser nulo ou vazio"),
                arguments("a", DESCRICAO, DURACAO, "Nome deve ter pelo menos 2 digitos"),
                arguments(NOME, null, DURACAO, "Descricao nao pode ser nula ou vazia"),
                arguments(NOME, "          ", DURACAO, "Descricao nao pode ser nula ou vazia"),
                arguments(NOME, "xxxxxxxxx", DURACAO, "Descricao deve ter pelo menos 10 digitos"),
                arguments(NOME, DESCRICAO, null, "Duracao nao pode ser vazia ou nula"));
    }

    private void assertFields(final CursoDtoRequest CursoRequest) {
        final Set<ConstraintViolation<CursoDtoRequest>> violations = VALIDATOR.validate(CursoRequest);
        assertThat(violations).isEmpty();

        assertThat(CursoRequest.getNome()).isEqualTo(NOME);
        assertThat(CursoRequest.getDescricao()).isEqualTo(DESCRICAO);
        assertThat(CursoRequest.getDuracao()).isEqualTo(DURACAO);
    }
}
