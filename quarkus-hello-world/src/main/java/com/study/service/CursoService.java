package com.study.service;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.study.dto.CursoDtoRequest;
import com.study.dto.CursoDtoResponse;
import com.study.mapper.CursoMapper;
import com.study.model.Curso;
import com.study.repository.CursoRepository;

@RequestScoped
public class CursoService {
    @Inject
    private CursoRepository repository;

    @Inject
    private CursoMapper cursoMapper;

    @Transactional
    public CursoDtoResponse cadastrar(@Valid CursoDtoRequest curso) {
        Curso entityCurso = cursoMapper.toEntity(curso);
        repository.persistAndFlush(entityCurso);
        return cursoMapper.toResponse(entityCurso);
    }

    @Transactional
    public List<CursoDtoResponse> listarTodos() {
        return cursoMapper.toListResponse(repository.listAll());
    }

    @Transactional
    public CursoDtoResponse buscarPorId(Integer id) {
        return cursoMapper.toResponse(repository.findById(id));
    }

    @Transactional
    public List<CursoDtoResponse> burcarPorNome(String nome) {
        return cursoMapper.toListResponse(repository.find("Nome LIKE ?1", "%" + nome + "%").list());
    }

    @Transactional
    public CursoDtoResponse alterar(Integer id, @Valid CursoDtoRequest curso) {
        Curso cursoExistente = buscar(id);
        if (Objects.isNull(cursoExistente)) {
            return cursoMapper.toResponse(new Curso());
        }
        cursoExistente.setNome(curso.getNome());
        cursoExistente.setDescricao(curso.getDescricao());
        cursoExistente.setDuracao(curso.getDuracao());
        repository.persistAndFlush(cursoExistente);
        return cursoMapper.toResponse(cursoExistente);
    }

    @Transactional
    public Boolean excluir(Integer id) {
        return repository.deleteById(id);
    }

    private Curso buscar(Integer id) {
        return repository.findById(id);
    }
}
