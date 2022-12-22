package com.study.service;

import java.util.List;

import com.study.dao.CursoDao;
import com.study.dto.CursoDtoRequest;
import com.study.dto.CursoDtoResponse;
import com.study.mapper.CursoMapper;
import com.study.model.Curso;

public class CursoService {
    private CursoDao cursoDao = new CursoDao();
    private CursoMapper cursoMapper = new CursoMapper();

    public CursoDtoResponse cadastrar(Curso curso) {
        return cursoMapper.toResponse(cursoDao.addCurso(curso));
    }

    public List<CursoDtoResponse> ListarTodos() {
        return cursoMapper.toListResponse(cursoDao.getCursos());
    }

    public CursoDtoResponse BuscarPorId(Integer id) {
        return cursoMapper.toResponse(cursoDao.getCursoById(id));
    }

    public List<CursoDtoResponse> BurcarPorNome(String nome) {
        return cursoMapper.toListResponse(cursoDao.getCursoByName(nome));
    }

    public CursoDtoResponse alterar(Integer id, CursoDtoRequest curso) {
        return cursoMapper.toResponse(cursoDao.alterarCurso(id,cursoMapper.toEntity(curso)));
    }

    public CursoDtoResponse excluir(Integer id) {
        return cursoMapper.toResponse(cursoDao.excluirCurso(id));
    }
}
