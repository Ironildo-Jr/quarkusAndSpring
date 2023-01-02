package com.study.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.study.dto.AlunoDtoResponse;
import com.study.dto.ProfessorDtoResponse;
import com.study.enums.SEXO;
import com.study.enums.TITULO;
import com.study.model.Aluno;
import com.study.model.Professor;

public class AlunoMapperTest {
    private static final Integer ID_1 = 10;
    private static final Integer ID_2 = 20;
    private static final String NOME_1 = "nome 1";
    private static final String NOME_2 = "nome 2";
    private static final SEXO SEXO_1 = SEXO.MASCULINO;
    private static final SEXO SEXO_2 = SEXO.FEMININO;
    private static final String MATRICULA_1 = "F0000000";
    private static final String MATRICULA_2 = "F1111111";
    private static final Professor PROFESSOR_1 = Professor.builder().id(ID_1).nome(NOME_1).sexo(SEXO_1)
            .titulo(TITULO.DOUTORADO).build();
    private static final ProfessorDtoResponse PROFESSOR_RESPONSE_1 = ProfessorDtoResponse.builder().id(ID_1)
            .nome(NOME_1).sexo(SEXO_1)
            .titulo(TITULO.DOUTORADO).build();
    private static final ProfessorDtoResponse PROFESSOR_RESPONSE_2 = ProfessorDtoResponse.builder().id(ID_1)
            .nome(NOME_1).sexo(SEXO_1)
            .titulo(TITULO.DOUTORADO).build();
    private static final Professor PROFESSOR_2 = Professor.builder().id(ID_2).nome(NOME_2).sexo(SEXO_2)
            .titulo(TITULO.DOUTORADO).build();
    private static final Aluno ALUNO_1 = new Aluno(ID_1, NOME_1, SEXO_1, MATRICULA_1, PROFESSOR_1);
    private static final Aluno ALUNO_2 = new Aluno(ID_2, NOME_2, SEXO_2, MATRICULA_2, PROFESSOR_2);

    private static final AlunoDtoResponse ALUNO_RESPONSE_1 = new AlunoDtoResponse(ID_1, NOME_1, SEXO_1, MATRICULA_1,
            PROFESSOR_RESPONSE_1);

    private static final AlunoDtoResponse ALUNO_RESPONSE_2 = new AlunoDtoResponse(ID_2, NOME_2, SEXO_2, MATRICULA_2,
            PROFESSOR_RESPONSE_2);

    private ProfessorMapper professorMapper = Mockito.mock(ProfessorMapper.class);
    private final AlunoMapper mapper = new AlunoMapper(professorMapper);

    @Test
    void toResponse() {
        // GIVEN
        given(professorMapper.toResponse(PROFESSOR_1)).willReturn(PROFESSOR_RESPONSE_1);

        // WHEN
        AlunoDtoResponse actual = mapper.toResponse(ALUNO_1);

        // THEN
        assertThat(actual).isEqualTo(ALUNO_RESPONSE_1);
    }

    @Test
    void toResponseAlunoNull() {
        // GIVEN

        // WHEN
        AlunoDtoResponse actual = mapper.toResponse(null);

        // THEN
        assertThat(actual).isNull();
    }

    @Test
    void toResponseProfessorNull() {
        // GIVEN
        Aluno aluno = new Aluno(ID_1, NOME_1, SEXO_1, MATRICULA_1, null);
        AlunoDtoResponse alunoResponse = new AlunoDtoResponse(ID_1, NOME_1, SEXO_1, MATRICULA_1, null);
        given(professorMapper.toResponse(null)).willReturn(null);

        // WHEN
        AlunoDtoResponse actual = mapper.toResponse(aluno);

        // THEN
        assertThat(actual).isEqualTo(alunoResponse);
    }

    @Test
    void toResponseList() {
        // GIVEN
        List<Aluno> alunos = List.of(ALUNO_1, ALUNO_2);
        given(professorMapper.toResponse(PROFESSOR_1)).willReturn(PROFESSOR_RESPONSE_1);
        given(professorMapper.toResponse(PROFESSOR_2)).willReturn(PROFESSOR_RESPONSE_2);

        // WHEN
        List<AlunoDtoResponse> actual = mapper.toListResponse(alunos);

        // THEN
        assertThat(actual).containsExactly(ALUNO_RESPONSE_1, ALUNO_RESPONSE_2);
    }

    @Test
    void toResponseListNull() {
        // GIVEN

        // WHEN
        List<AlunoDtoResponse> actual = mapper.toListResponse(null);

        // THEN
        assertThat(actual).isEmpty();
    }

    @Test
    void toResponseListVazia() {
        // GIVEN

        // WHEN
        List<AlunoDtoResponse> actual = mapper.toListResponse(new ArrayList<Aluno>());

        // THEN
        assertThat(actual).isEmpty();
    }
}
