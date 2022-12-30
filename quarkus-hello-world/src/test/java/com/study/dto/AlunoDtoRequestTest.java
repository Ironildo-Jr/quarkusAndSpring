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

import nl.jqno.equalsverifier.EqualsVerifier;

public class AlunoDtoRequestTest {
    private static final String NOME = "nome";
    private static final SEXO SEXOA = SEXO.MASCULINO;
    private static final String MATRICULA = "F0000000";

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
        AlunoDtoRequest alunoRequest = AlunoDtoRequest.builder().nome(NOME).sexo(SEXOA).matricula(MATRICULA).build();

        // THEN
        assertFields(alunoRequest);
    }

    @Test
    void construtorAllArgs() {
        // GIVEN

        // WHEN
        AlunoDtoRequest alunoRequest = new AlunoDtoRequest(MATRICULA, NOME, SEXOA);

        // THEN
        assertFields(alunoRequest);
    }

    @Test
    void construtorPadrao() {
        // GIVEN

        // WHEN
        AlunoDtoRequest alunoRequest = new AlunoDtoRequest();
        alunoRequest.setNome(NOME);
        alunoRequest.setSexo(SEXOA);
        alunoRequest.setMatricula(MATRICULA);

        // THEN
        assertFields(alunoRequest);
    }

    @ParameterizedTest
    @MethodSource("CamposInvalidos")
    void construtor_CamposInvalidos(final String nome, final String matricula, final String errorMessage) {
        // GIVEN

        // WHEN
        AlunoDtoRequest alunoRequest = new AlunoDtoRequest();
        alunoRequest.setNome(nome);
        alunoRequest.setSexo(SEXOA);
        alunoRequest.setMatricula(matricula);

        // THEN
        final Set<ConstraintViolation<AlunoDtoRequest>> violations = VALIDATOR.validate(alunoRequest);
        assertThat(violations)
                .isNotEmpty()
                .hasSize(1);
        assertThat(violations.stream().findFirst().get().getMessage()).isEqualTo(errorMessage);
    }

    @Test
    void equalsAndHashcodeContract() {
        EqualsVerifier.simple().forClass(AlunoDtoRequest.class)
                .verify();
    }

    static Stream<Arguments> CamposInvalidos() {
        return Stream.of(
                arguments(null, MATRICULA, "Nome nao pode ser nulo ou vazio"),
                arguments("  ", MATRICULA, "Nome nao pode ser nulo ou vazio"),
                arguments("a", MATRICULA, "Nome deve ter pelo menos 2 digitos"),
                arguments(NOME, null, "Matricula nao pode ser nula ou vazia"),
                arguments(NOME, "        ", "Matricula nao pode ser nula ou vazia"),
                arguments(NOME, "xxxxxxx", "Matricula deve ter pelo menos 8 digitos"),
                arguments(NOME, "xxxxxxxxx", "Matricula deve ter no maximo 8 digitos"));
    }

    private void assertFields(final AlunoDtoRequest alunoRequest) {
        final Set<ConstraintViolation<AlunoDtoRequest>> violations = VALIDATOR.validate(alunoRequest);
        assertThat(violations).isEmpty();

        assertThat(alunoRequest.getNome()).isEqualTo(NOME);
        assertThat(alunoRequest.getSexo()).isEqualTo(SEXOA);
        assertThat(alunoRequest.getMatricula()).isEqualTo(MATRICULA);
    }
}