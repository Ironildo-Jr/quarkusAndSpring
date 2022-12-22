package com.study.dao;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.study.model.Professor;
import com.study.repository.ProfessorRepository;

@RequestScoped
public class ProfessorDao {
    @Inject
    private ProfessorRepository repository;

    public Professor addProfessor(Professor professor) {
        repository.persistAndFlush(professor);
        return professor;
    }

    public List<Professor> getProfessores() {
        return repository.listAll();
    }

    public Professor getProfessorById(Integer id) {
        return repository.findById(id);
    }

    public List<Professor> getProfessorByName(String nome) {
        return repository.find("Nome LIKE ?1", "%" + nome + "%").list();
    }

    public Professor alterarProfessor(Integer id, Professor professor) {
        Professor professorExistente = getProfessorById(id);
        if (Objects.isNull(professorExistente)) {
            return new Professor();
        }
        professorExistente.setNome(professor.getNome());
        professorExistente.setTitulo(professor.getTitulo());
        professorExistente.setSexo(professor.getSexo());
        repository.persistAndFlush(professorExistente);
        return professorExistente;
    }

    public Professor excluirProfessor(Integer id) {
        Professor professor = getProfessorById(id);
        repository.delete(professor);
        return professor;
    }
}
