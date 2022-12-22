package com.study.dao;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.study.model.Curso;
import com.study.repository.CursoRepository;

@RequestScoped
public class CursoDao {
    @Inject
    private CursoRepository repository;

    public Curso addCurso(Curso curso) {
        repository.persistAndFlush(curso);
        return curso;
    }

    public List<Curso> getCursos() {
        return repository.listAll();
    }

    public Curso getCursoById(Integer id) {
        return repository.findById(id);
    }

    public List<Curso> getCursoByName(String nome) {
        return repository.find("Nome LIKE ?1", "%" + nome + "%").list();
    }

    public Curso alterarCurso(Integer id, Curso curso) {
        Curso cursoExistente = getCursoById(id);
        if (Objects.isNull(cursoExistente)) {
            return new Curso();
        }
        cursoExistente.setNome(curso.getNome());
        cursoExistente.setDescricao(curso.getDescricao());
        cursoExistente.setDuracao(curso.getDuracao());
        repository.persistAndFlush(cursoExistente);
        return cursoExistente;
    }

    public Curso excluirCurso(Integer id) {
        Curso curso = getCursoById(id);
        repository.delete(curso);
        return curso;
    }
}
