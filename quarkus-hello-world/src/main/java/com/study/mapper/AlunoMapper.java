package com.study.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.study.dto.AlunoDtoRequest;
import com.study.dto.AlunoDtoResponse;
import com.study.model.Aluno;

@RequestScoped
public class AlunoMapper {

    @Inject
    ProfessorMapper professorMapper;

    public AlunoDtoResponse toResponse(Aluno aluno) {
        return new AlunoDtoResponse(aluno.getId(), aluno.getNome(), aluno.getSexo(), aluno.getMatricula(), professorMapper.toResponse(aluno.getProfessor()));
    }

    public List<AlunoDtoResponse> toListResponse(List<Aluno> alunos) {
        List<AlunoDtoResponse> alunosResponse = new ArrayList<AlunoDtoResponse>();
        alunos.stream().forEach(a -> alunosResponse.add(toResponse(a)));
        return alunosResponse;
    }

    public Aluno toEntity(AlunoDtoRequest alunoRequest) {
        return new Aluno(null, alunoRequest.getNome(), alunoRequest.getSexo(), alunoRequest.getMatricula(), null);
    }
}
