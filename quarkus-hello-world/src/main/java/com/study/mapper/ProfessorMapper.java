package com.study.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import com.study.dto.ProfessorDtoRequest;
import com.study.dto.ProfessorDtoResponse;
import com.study.model.Professor;

@RequestScoped
public class ProfessorMapper {
    public ProfessorDtoResponse toResponse(Professor professor) {
        return new ProfessorDtoResponse(professor.getId(), professor.getNome(), professor.getSexo(),
                professor.getTitulo());
    }

    public List<ProfessorDtoResponse> toListResponse(List<Professor> professores) {
        List<ProfessorDtoResponse> professoresResponse = new ArrayList<ProfessorDtoResponse>();
        professores.stream().forEach(a -> professoresResponse.add(toResponse(a)));
        return professoresResponse;
    }

    public Professor toEntity(ProfessorDtoRequest professorResquest) {
        return new Professor(null, professorResquest.getNome(), professorResquest.getSexo(),
                professorResquest.getTitulo());
    }
}
