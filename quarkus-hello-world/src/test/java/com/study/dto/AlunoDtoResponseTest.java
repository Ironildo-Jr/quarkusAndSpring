package com.study.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;

import com.study.enums.SEXO;
import com.study.enums.TITULO;

import nl.jqno.equalsverifier.EqualsVerifier;

public class AlunoDtoResponseTest {
    private static final Integer ID = 10;
    private static final String NOME = "nome";
    private static final SEXO SEXOA = SEXO.MASCULINO;
    private static final String MATRICULA = "F0000000";
    private static final ProfessorDtoResponse PROFESSOR = ProfessorDtoResponse.builder().id(ID).nome(NOME).sexo(SEXOA)
            .titulo(TITULO.DOUTORADO).build();

    final static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final static Validator VALIDATOR = factory.getValidator();

    @Test
    void construtorBuilder() {
        // GIVEN

        // WHEN
        AlunoDtoResponse alunoResponse = AlunoDtoResponse.builder().id(ID).nome(NOME).sexo(SEXOA).matricula(MATRICULA).professor(PROFESSOR).build();

        // THEN
        assertFields(alunoResponse);
    }

    @Test
    void construtorAllArgs() {
        // GIVEN

        // WHEN
        AlunoDtoResponse alunoResponse = new AlunoDtoResponse(ID, NOME, SEXOA, MATRICULA, PROFESSOR);

        // THEN
        assertFields(alunoResponse);
    }

    @Test
    void construtorPadrao() {
        // GIVEN

        // WHEN
        AlunoDtoResponse alunoReponse = new AlunoDtoResponse();
        alunoReponse.setId(ID);
        alunoReponse.setNome(NOME);
        alunoReponse.setSexo(SEXOA);
        alunoReponse.setMatricula(MATRICULA);
        alunoReponse.setProfessor(PROFESSOR);

        // THEN
        assertFields(alunoReponse);
    }

    @Test
    void equalsAndHashcodeContract() {
        EqualsVerifier.simple().forClass(AlunoDtoResponse.class)
                .withPrefabValues(ProfessorDtoResponse.class,
                        PROFESSOR, new ProfessorDtoResponse())
                .verify();
    }

    private void assertFields(final AlunoDtoResponse alunoResponse) {
        final Set<ConstraintViolation<AlunoDtoResponse>> violations = VALIDATOR.validate(alunoResponse);
        assertThat(violations).isEmpty();

        assertThat(alunoResponse.getNome()).isEqualTo(NOME);
        assertThat(alunoResponse.getSexo()).isEqualTo(SEXOA);
        assertThat(alunoResponse.getMatricula()).isEqualTo(MATRICULA);
    }
}
