package com.study.mapper;

import java.util.ArrayList;
import java.util.List;

import com.study.dto.CursoDtoRequest;
import com.study.dto.CursoDtoResponse;
import com.study.model.Curso;

public class CursoMapper {
    public CursoDtoResponse toResponse(Curso curso){
        return new CursoDtoResponse(curso.getNome(), curso.getDescricao(), curso.getDuracao());
    }

    public List<CursoDtoResponse> toListResponse(List<Curso> cursos) {
        List<CursoDtoResponse> cursosResponse = new ArrayList<CursoDtoResponse>();
        cursos.stream().forEach(c -> cursosResponse.add(toResponse(c)));
        return cursosResponse;
    }

    public Curso toEntity(CursoDtoRequest cursoRequest) {
        return new Curso(0, cursoRequest.getNome(), null, 0);
    }
}
