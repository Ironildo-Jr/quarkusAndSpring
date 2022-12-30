package com.study.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CursoDtoResponseTest {
    private static final Integer ID = 10;
    private static final String NOME = "nome";
    private static final String DESCRICAO = "Qualquer coisa";
    private static final Integer DURACAO = 100;

    final static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final static Validator VALIDATOR = factory.getValidator();

    @Test
    void construtorBuilder() {
        // GIVEN

        // WHEN
        CursoDtoResponse cursoResponse = CursoDtoResponse.builder().id(ID).nome(NOME).descricao(DESCRICAO).duracao(DURACAO).build();

        // THEN
        assertFields(cursoResponse);
    }

    @Test
    void construtorAllArgs() {
        // GIVEN

        // WHEN
        CursoDtoResponse cursoResponse = new CursoDtoResponse(ID, NOME, DESCRICAO, DURACAO);

        // THEN
        assertFields(cursoResponse);
    }

    @Test
    void construtorPadrao() {
        // GIVEN

        // WHEN
        CursoDtoResponse cursoReponse = new CursoDtoResponse();
        cursoReponse.setId(ID);
        cursoReponse.setNome(NOME);
        cursoReponse.setDescricao(DESCRICAO);
        cursoReponse.setDuracao(DURACAO);

        // THEN
        assertFields(cursoReponse);
    }

    @Test
    void equalsAndHashcodeContract() {
        EqualsVerifier.simple().forClass(CursoDtoResponse.class)
                .verify();
    }

    private void assertFields(final CursoDtoResponse cursoResponse) {
        final Set<ConstraintViolation<CursoDtoResponse>> violations = VALIDATOR.validate(cursoResponse);
        assertThat(violations).isEmpty();

        assertThat(cursoResponse.getNome()).isEqualTo(NOME);
        assertThat(cursoResponse.getDescricao()).isEqualTo(DESCRICAO);
        assertThat(cursoResponse.getDuracao()).isEqualTo(DURACAO);
    }
}
