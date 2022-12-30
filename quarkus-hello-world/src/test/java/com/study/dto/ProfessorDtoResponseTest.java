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

public class ProfessorDtoResponseTest {
    private static final Integer ID = 10;
    private static final String NOME = "nome";
    private static final SEXO SEXOA = SEXO.MASCULINO;
    private static final TITULO TITULOA = TITULO.DOUTORADO;
    private static final CursoDtoResponse CURSO = CursoDtoResponse.builder().id(ID).nome(NOME).descricao("Qualquer coisa").duracao(100).build();

    final static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final static Validator VALIDATOR = factory.getValidator();

    @Test
    void construtorBuilder() {
        // GIVEN

        // WHEN
        ProfessorDtoResponse professorResponse = ProfessorDtoResponse.builder().id(ID).nome(NOME).sexo(SEXOA).titulo(TITULOA).curso(CURSO).build();

        // THEN
        assertFields(professorResponse);
    }

    @Test
    void construtorAllArgs() {
        // GIVEN

        // WHEN
        ProfessorDtoResponse professorResponse = new ProfessorDtoResponse(ID, NOME, SEXOA, TITULOA, CURSO);

        // THEN
        assertFields(professorResponse);
    }

    @Test
    void construtorPadrao() {
        // GIVEN

        // WHEN
        ProfessorDtoResponse alunoReponse = new ProfessorDtoResponse();
        alunoReponse.setId(ID);
        alunoReponse.setNome(NOME);
        alunoReponse.setSexo(SEXOA);
        alunoReponse.setTitulo(TITULOA);;
        alunoReponse.setCurso(CURSO);;

        // THEN
        assertFields(alunoReponse);
    }

    @Test
    void equalsAndHashcodeContract() {
        EqualsVerifier.simple().forClass(ProfessorDtoResponse.class)
                .withPrefabValues(CursoDtoResponse.class,
                        CURSO, new CursoDtoResponse())
                .verify();
    }

    private void assertFields(final ProfessorDtoResponse professorResponse) {
        final Set<ConstraintViolation<ProfessorDtoResponse>> violations = VALIDATOR.validate(professorResponse);
        assertThat(violations).isEmpty();

        assertThat(professorResponse.getNome()).isEqualTo(NOME);
        assertThat(professorResponse.getSexo()).isEqualTo(SEXOA);
        assertThat(professorResponse.getTitulo()).isEqualTo(TITULOA);
    }
}

