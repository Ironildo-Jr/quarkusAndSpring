package com.study.dao;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.study.model.Aluno;
import com.study.repository.AlunoRepository;

@RequestScoped
public class AlunoDao {

    @Inject
    private AlunoRepository repository;

    public Aluno addAluno(Aluno aluno) {
        repository.persistAndFlush(aluno);
        return aluno;
    }

    public List<Aluno> getAlunos() {
        return repository.listAll();
    }

    public Aluno getAlunoById(Integer id) {
        return repository.findById(id);
    }

    public List<Aluno> getAlunoByName(String nome) {
        return repository.find("Nome LIKE ?1", "%" + nome + "%").list();
    }

    public Aluno alterarAluno(Integer id, Aluno aluno) {
        Aluno alunoExistente = getAlunoById(id);
        if (Objects.isNull(alunoExistente)) {
            return new Aluno();
        }
        alunoExistente.setNome(aluno.getNome());
        alunoExistente.setMatricula(aluno.getMatricula());
        alunoExistente.setSexo(aluno.getSexo());
        repository.persistAndFlush(alunoExistente);
        return alunoExistente;
    }

    public Aluno excluirAluno(Integer id) {
        Aluno aluno = getAlunoById(id);
        repository.delete(aluno);
        return aluno;
    }
}
