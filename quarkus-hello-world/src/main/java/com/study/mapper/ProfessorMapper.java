package com.study.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;

import com.study.dto.ProfessorDtoRequest;
import com.study.dto.ProfessorDtoResponse;
import com.study.model.Professor;

import lombok.RequiredArgsConstructor;

@RequestScoped
@RequiredArgsConstructor
public class ProfessorMapper {

    private final CursoMapper cursoMapper;

    public ProfessorDtoResponse toResponse(Professor professor) {
        if (Objects.isNull(professor))
            return null;
        return new ProfessorDtoResponse(professor.getId(), professor.getNome(), professor.getSexo(),
                professor.getTitulo(), cursoMapper.toResponse(professor.getCurso()));
    }

    public List<ProfessorDtoResponse> toListResponse(List<Professor> professores) {
        List<ProfessorDtoResponse> professoresResponse = new ArrayList<ProfessorDtoResponse>();
        if (Objects.isNull(professores) || professores.isEmpty())
            return professoresResponse;
        professores.stream().forEach(a -> professoresResponse.add(toResponse(a)));
        return professoresResponse;
    }

    public Professor toEntity(ProfessorDtoRequest professorResquest) {
        return new Professor(null, professorResquest.getNome(), professorResquest.getSexo(),
                professorResquest.getTitulo(), null, null);
    }
}
