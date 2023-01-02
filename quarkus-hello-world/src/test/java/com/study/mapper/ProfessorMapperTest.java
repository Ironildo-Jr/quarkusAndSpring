package com.study.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.study.dto.CursoDtoResponse;
import com.study.dto.ProfessorDtoResponse;
import com.study.enums.SEXO;
import com.study.enums.TITULO;
import com.study.model.Curso;
import com.study.model.Professor;

public class ProfessorMapperTest {
    private static final Integer ID_1 = 10;
    private static final Integer ID_2 = 20;
    private static final String NOME_1 = "nome 1";
    private static final String NOME_2 = "nome 2";
    private static final SEXO SEXO_1 = SEXO.MASCULINO;
    private static final SEXO SEXO_2 = SEXO.FEMININO;
    private static final TITULO TITULO_1 = TITULO.DOUTORADO;
    private static final TITULO TITULO_2 = TITULO.MESTRADO;
    private static final Curso CURSO_1 = Curso.builder().id(ID_1).nome(NOME_1).descricao("Qualquer coisa").duracao(100)
            .build();
    private static final Curso CURSO_2 = Curso.builder().id(ID_2).nome(NOME_2).descricao("Qualquer coisa2").duracao(200)
            .build();
    private static final CursoDtoResponse CURSO_RESPONSE_1 = CursoDtoResponse.builder().id(ID_1)
            .nome(NOME_1).descricao("Qualquer coisa").duracao(100).build();
    private static final CursoDtoResponse CURSO_RESPONSE_2 = CursoDtoResponse.builder().id(ID_1)
            .descricao("Qualquer coisa2").duracao(200).build();

    private static final Professor PROFESSOR_1 = new Professor(ID_1, NOME_1, SEXO_1, TITULO_1, CURSO_1, null);
    private static final Professor PROFESSOR_2 = new Professor(ID_2, NOME_2, SEXO_2, TITULO_2, CURSO_2, null);

    private static final ProfessorDtoResponse PROFESSOR_RESPONSE_1 = new ProfessorDtoResponse(ID_1, NOME_1, SEXO_1,
            TITULO_1,
            CURSO_RESPONSE_1);

    private static final ProfessorDtoResponse PROFESSOR_RESPONSE_2 = new ProfessorDtoResponse(ID_2, NOME_2, SEXO_2,
            TITULO_2,
            CURSO_RESPONSE_2);

    private CursoMapper cursoMapper = Mockito.mock(CursoMapper.class);
    private final ProfessorMapper mapper = new ProfessorMapper(cursoMapper);

    @Test
    void toResponse() {
        // GIVEN
        given(cursoMapper.toResponse(CURSO_1)).willReturn(CURSO_RESPONSE_1);

        // WHEN
        ProfessorDtoResponse actual = mapper.toResponse(PROFESSOR_1);

        // THEN
        assertThat(actual).isEqualTo(PROFESSOR_RESPONSE_1);
    }

    @Test
    void toResponseProfessorNull() {
        // GIVEN

        // WHEN
        ProfessorDtoResponse actual = mapper.toResponse(null);

        // THEN
        assertThat(actual).isNull();
    }

    @Test
    void toResponseCursoNull() {
        // GIVEN
        Professor professor = new Professor(ID_1, NOME_1, SEXO_1, TITULO_1, null, null);
        ProfessorDtoResponse alunoResponse = new ProfessorDtoResponse(ID_1, NOME_1, SEXO_1, TITULO_1, null);
        given(cursoMapper.toResponse(null)).willReturn(null);

        // WHEN
        ProfessorDtoResponse actual = mapper.toResponse(professor);

        // THEN
        assertThat(actual).isEqualTo(alunoResponse);
    }

    @Test
    void toResponseList() {
        // GIVEN
        List<Professor> professores = List.of(PROFESSOR_1, PROFESSOR_2);
        given(cursoMapper.toResponse(CURSO_1)).willReturn(CURSO_RESPONSE_1);
        given(cursoMapper.toResponse(CURSO_2)).willReturn(CURSO_RESPONSE_2);

        // WHEN
        List<ProfessorDtoResponse> actual = mapper.toListResponse(professores);

        // THEN
        assertThat(actual).containsExactly(PROFESSOR_RESPONSE_1, PROFESSOR_RESPONSE_2);
    }

    @Test
    void toResponseListNull() {
        // GIVEN

        // WHEN
        List<ProfessorDtoResponse> actual = mapper.toListResponse(null);

        // THEN
        assertThat(actual).isEmpty();
    }

    @Test
    void toResponseListVazia() {
        // GIVEN

        // WHEN
        List<ProfessorDtoResponse> actual = mapper.toListResponse(new ArrayList<Professor>());

        // THEN
        assertThat(actual).isEmpty();
    }
}
