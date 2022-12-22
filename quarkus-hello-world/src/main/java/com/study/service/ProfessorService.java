package com.study.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.study.dao.ProfessorDao;
import com.study.dto.ProfessorDtoRequest;
import com.study.dto.ProfessorDtoResponse;
import com.study.mapper.ProfessorMapper;

@RequestScoped
public class ProfessorService {

    @Inject
    private ProfessorDao professorDao;

    @Inject
    private ProfessorMapper professorMapper;

    @Transactional
    public ProfessorDtoResponse cadastrar(ProfessorDtoRequest professor) {
        return professorMapper.toResponse(professorDao.addProfessor(professorMapper.toEntity(professor)));
    }

    @Transactional
    public List<ProfessorDtoResponse> ListarTodos() {
        return professorMapper.toListResponse(professorDao.getProfessores());
    }

    @Transactional
    public ProfessorDtoResponse BuscarPorId(Integer id) {
        return professorMapper.toResponse(professorDao.getProfessorById(id));
    }

    @Transactional
    public List<ProfessorDtoResponse> BurcarPorNome(String nome) {
        return professorMapper.toListResponse(professorDao.getProfessorByName(nome));
    }

    @Transactional
    public ProfessorDtoResponse alterar(Integer id, ProfessorDtoRequest professor) {
        return professorMapper.toResponse(professorDao.alterarProfessor(id, professorMapper.toEntity(professor)));
    }

    @Transactional
    public ProfessorDtoResponse excluir(Integer id) {
        return professorMapper.toResponse(professorDao.excluirProfessor(id));
    }
}
