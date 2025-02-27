package com.study.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;

import com.study.dto.CursoDtoRequest;
import com.study.dto.CursoDtoResponse;
import com.study.model.Curso;

@RequestScoped
public class CursoMapper {
    public CursoDtoResponse toResponse(Curso curso) {
        if (Objects.isNull(curso))
            return null;
        return new CursoDtoResponse(curso.getId(), curso.getNome(), curso.getDescricao(), curso.getDuracao());
    }

    public List<CursoDtoResponse> toListResponse(List<Curso> cursos) {
        List<CursoDtoResponse> cursosResponse = new ArrayList<CursoDtoResponse>();
        if (Objects.isNull(cursos) || cursos.isEmpty())
            return cursosResponse;
        cursos.stream().forEach(a -> cursosResponse.add(toResponse(a)));
        return cursosResponse;
    }

    public Curso toEntity(CursoDtoRequest cursoRequest) {
        return new Curso(null, cursoRequest.getNome(), cursoRequest.getDescricao(), cursoRequest.getDuracao(), null);
    }
}
