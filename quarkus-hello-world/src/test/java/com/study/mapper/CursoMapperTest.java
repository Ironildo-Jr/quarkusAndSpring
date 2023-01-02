package com.study.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.study.dto.CursoDtoResponse;
import com.study.model.Curso;

public class CursoMapperTest {
    private static final Integer ID_1 = 10;
    private static final Integer ID_2 = 20;
    private static final String NOME_1 = "nome 1";
    private static final String NOME_2 = "nome 2";
    private static final String DESCRICAO_1 = "Qualquer coisa 1";
    private static final String DESCRICAO_2 = "Qualquer coisa 2";
    private static final Integer DURACAO_1 = 100;
    private static final Integer DURACAO_2 = 200;

    private static final Curso CURSO_1 = new Curso(ID_1, NOME_1, DESCRICAO_1, DURACAO_1, null);
    private static final Curso CURSO_2 = new Curso(ID_2, NOME_2, DESCRICAO_2, DURACAO_2, null);

    private static final CursoDtoResponse CURSO_RESPONSE_1 = new CursoDtoResponse(ID_1, NOME_1, DESCRICAO_1, DURACAO_1);

    private static final CursoDtoResponse CURSO_RESPONSE_2 = new CursoDtoResponse(ID_2, NOME_2, DESCRICAO_2, DURACAO_2);

    private final CursoMapper mapper = new CursoMapper();

    @Test
    void toResponse() {
        // GIVEN

        // WHEN
        CursoDtoResponse actual = mapper.toResponse(CURSO_1);

        // THEN
        assertThat(actual).isEqualTo(CURSO_RESPONSE_1);
    }

    @Test
    void toResponseCursoNull() {
        // GIVEN

        // WHEN
        CursoDtoResponse actual = mapper.toResponse(null);

        // THEN
        assertThat(actual).isNull();
    }

    @Test
    void toResponseList() {
        // GIVEN
        List<Curso> cursos = List.of(CURSO_1, CURSO_2);

        // WHEN
        List<CursoDtoResponse> actual = mapper.toListResponse(cursos);

        // THEN
        assertThat(actual).containsExactly(CURSO_RESPONSE_1, CURSO_RESPONSE_2);
    }

    @Test
    void toResponseListNull() {
        // GIVEN

        // WHEN
        List<CursoDtoResponse> actual = mapper.toListResponse(null);

        // THEN
        assertThat(actual).isEmpty();
    }

    @Test
    void toResponseListVazia() {
        // GIVEN

        // WHEN
        List<CursoDtoResponse> actual = mapper.toListResponse(new ArrayList<Curso>());

        // THEN
        assertThat(actual).isEmpty();
    }
}
