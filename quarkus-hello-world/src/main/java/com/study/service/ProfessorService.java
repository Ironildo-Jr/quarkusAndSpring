package com.study.service;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.study.dto.ProfessorDtoRequest;
import com.study.dto.ProfessorDtoResponse;
import com.study.mapper.ProfessorMapper;
import com.study.model.Professor;
import com.study.repository.ProfessorRepository;

@RequestScoped
public class ProfessorService {

    @Inject
    private ProfessorRepository repository;

    @Inject
    private ProfessorMapper professorMapper;

    @Transactional
    public ProfessorDtoResponse cadastrar(ProfessorDtoRequest professor) {
        Professor entityProfessor = professorMapper.toEntity(professor);
        repository.persistAndFlush(entityProfessor);
        return professorMapper.toResponse(entityProfessor);
    }

    @Transactional
    public List<ProfessorDtoResponse> listarTodos() {
        return professorMapper.toListResponse(repository.listAll());
    }

    @Transactional
    public ProfessorDtoResponse buscarPorId(Integer id) {
        return professorMapper.toResponse(buscar(id));
    }

    @Transactional
    public List<ProfessorDtoResponse> burcarPorNome(String nome) {
        return professorMapper.toListResponse(repository.find("Nome LIKE ?1", "%" + nome + "%").list());
    }

    @Transactional
    public ProfessorDtoResponse alterar(Integer id, ProfessorDtoRequest professor) {
        Professor professorExistente = buscar(id);
        if (Objects.isNull(professorExistente)) {
            return professorMapper.toResponse(new Professor());
        }
        professorExistente.setNome(professor.getNome());
        professorExistente.setSexo(professor.getSexo());
        repository.persistAndFlush(professorExistente);
        return professorMapper.toResponse(professorExistente);
    }

    @Transactional
    public Boolean excluir(Integer id) {
        return repository.deleteById(id);
    }

    private Professor buscar(Integer id) {
        return repository.findById(id);
    }
}
