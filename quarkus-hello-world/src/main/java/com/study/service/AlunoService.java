package com.study.service;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.study.dto.AlunoDtoRequest;
import com.study.dto.AlunoDtoResponse;
import com.study.mapper.AlunoMapper;
import com.study.model.Aluno;
import com.study.repository.AlunoRepository;

@RequestScoped
public class AlunoService {

    @Inject
    private AlunoRepository repository;

    @Inject
    private AlunoMapper alunoMapper;

    @Transactional
    public AlunoDtoResponse cadastrar(AlunoDtoRequest aluno) {
        Aluno entityAluno = alunoMapper.toEntity(aluno);
        repository.persistAndFlush(entityAluno);
        return alunoMapper.toResponse(entityAluno);
    }

    @Transactional
    public List<AlunoDtoResponse> listarTodos() {
        return alunoMapper.toListResponse(repository.listAll());
    }

    @Transactional
    public AlunoDtoResponse buscarPorId(Integer id) {
        return alunoMapper.toResponse(buscar(id));
    }

    @Transactional
    public List<AlunoDtoResponse> burcarPorNome(String nome) {
        return alunoMapper.toListResponse(repository.find("Nome LIKE ?1", "%" + nome + "%").list());
    }

    @Transactional
    public AlunoDtoResponse alterar(Integer id, AlunoDtoRequest aluno) {
        Aluno alunoExistente = buscar(id);
        if (Objects.isNull(alunoExistente)) {
            return alunoMapper.toResponse(new Aluno());
        }
        alunoExistente.setNome(aluno.getNome());
        alunoExistente.setSexo(aluno.getSexo());
        repository.persistAndFlush(alunoExistente);
        return alunoMapper.toResponse(alunoExistente);
    }

    @Transactional
    public Boolean excluir(Integer id) {
        return repository.deleteById(id);
    }

    private Aluno buscar(Integer id) {
        return repository.findById(id);
    }
}
