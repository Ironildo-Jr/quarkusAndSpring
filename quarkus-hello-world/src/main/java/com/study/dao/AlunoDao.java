package com.study.dao;

import java.util.ArrayList;
import java.util.List;

import com.study.model.Aluno;

public class AlunoDao {
    public Aluno addAluno(Aluno aluno) {
        return new Aluno();
    }

    public List<Aluno> getAlunos() {
        return new ArrayList<Aluno>();
    }

    public Aluno getAlunoById(Integer id) {
        return new Aluno();
    }

    public List<Aluno> getAlunoByName(String nome) {
        return new ArrayList<Aluno>();
    }

    public Aluno alterarAluno(Integer id,Aluno aluno){
        getAlunoById(id).setNome(aluno.getNome());
        return new Aluno();
    }

    public Aluno excluirAluno(Integer id){
        return new Aluno();
    }
}
