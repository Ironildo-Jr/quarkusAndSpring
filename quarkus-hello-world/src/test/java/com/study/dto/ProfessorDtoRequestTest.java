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

import com.study.enums.SEXO;
import com.study.enums.TITULO;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ProfessorDtoRequestTest {
    private static final String NOME = "nome";
    private static final SEXO SEXOA = SEXO.MASCULINO;
    private static final TITULO TITULOA = TITULO.DOUTORADO;

    final static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final static Validator VALIDATOR = factory.getValidator();

    @AfterAll
    static void fechar() {
        factory.close();
    }

    @Test
    void construtorBuilder() {
        // GIVEN

        // WHEN
        ProfessorDtoRequest cursoRequest = ProfessorDtoRequest.builder().nome(NOME).sexo(SEXOA).titulo(TITULOA).build();

        // THEN
        assertFields(cursoRequest);
    }

    @Test
    void construtorAllArgs() {
        // GIVEN

        // WHEN
        ProfessorDtoRequest cursoRequest = new ProfessorDtoRequest(NOME, SEXOA, TITULOA);

        // THEN
        assertFields(cursoRequest);
    }

    @Test
    void construtorPadrao() {
        // GIVEN

        // WHEN
        ProfessorDtoRequest cursoRequest = new ProfessorDtoRequest();
        cursoRequest.setNome(NOME);
        cursoRequest.setSexo(SEXOA);
        cursoRequest.setTitulo(TITULOA);

        // THEN
        assertFields(cursoRequest);
    }

    @ParameterizedTest
    @MethodSource("CamposInvalidos")
    void construtor_CamposInvalidos(final String nome, final TITULO titulo, final String errorMessage) {
        // GIVEN

        // WHEN
        ProfessorDtoRequest cursoRequest = new ProfessorDtoRequest();
        cursoRequest.setNome(nome);
        cursoRequest.setSexo(SEXOA);
        cursoRequest.setTitulo(titulo);

        // THEN
        final Set<ConstraintViolation<ProfessorDtoRequest>> violations = VALIDATOR.validate(cursoRequest);
        assertThat(violations)
                .isNotEmpty()
                .hasSize(1);
        assertThat(violations.stream().findFirst().get().getMessage()).isEqualTo(errorMessage);
    }

    @Test
    void equalsAndHashcodeContract() {
        EqualsVerifier.simple().forClass(ProfessorDtoRequest.class)
                .verify();
    }

    static Stream<Arguments> CamposInvalidos() {
        return Stream.of(
                arguments(null, TITULOA, "Nome nao pode ser nulo ou vazio"),
                arguments("  ", TITULOA, "Nome nao pode ser nulo ou vazio"),
                arguments("a", TITULOA, "Nome deve ter pelo menos 2 digitos"),
                arguments(NOME, null, "Titulo nao pode ser nulo"));
    }

    private void assertFields(final ProfessorDtoRequest cursoRequest) {
        final Set<ConstraintViolation<ProfessorDtoRequest>> violations = VALIDATOR.validate(cursoRequest);
        assertThat(violations).isEmpty();

        assertThat(cursoRequest.getNome()).isEqualTo(NOME);
        assertThat(cursoRequest.getSexo()).isEqualTo(SEXOA);
        assertThat(cursoRequest.getTitulo()).isEqualTo(TITULOA);
    }
}
