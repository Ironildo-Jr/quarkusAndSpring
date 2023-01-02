package com.study.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;

import com.study.dto.AlunoDtoRequest;
import com.study.dto.AlunoDtoResponse;
import com.study.model.Aluno;

import lombok.RequiredArgsConstructor;

@RequestScoped
@RequiredArgsConstructor
public class AlunoMapper {

    private final ProfessorMapper professorMapper;

    public AlunoDtoResponse toResponse(Aluno aluno) {
        if (Objects.isNull(aluno))
            return null;
        return new AlunoDtoResponse(aluno.getId(), aluno.getNome(), aluno.getSexo(), aluno.getMatricula(),
                professorMapper.toResponse(aluno.getProfessor()));
    }

    public List<AlunoDtoResponse> toListResponse(List<Aluno> alunos) {
        List<AlunoDtoResponse> alunosResponse = new ArrayList<AlunoDtoResponse>();
        if (Objects.isNull(alunos) || alunos.isEmpty())
            return alunosResponse;
        alunos.stream().forEach(a -> alunosResponse.add(toResponse(a)));
        return alunosResponse;
    }

    public Aluno toEntity(AlunoDtoRequest alunoRequest) {
        return new Aluno(null, alunoRequest.getNome(), alunoRequest.getSexo(), alunoRequest.getMatricula(), null);
    }
}
