package com.study.service;

import java.util.List;

import com.study.dao.AlunoDao;
import com.study.dto.AlunoDtoRequest;
import com.study.dto.AlunoDtoResponse;
import com.study.mapper.AlunoMapper;
import com.study.model.Aluno;

public class AlunoService {
    private AlunoDao alunoDao = new AlunoDao();
    private AlunoMapper alunoMapper = new AlunoMapper();

    public AlunoDtoResponse cadastrar(Aluno aluno) {
        return alunoMapper.toResponse(alunoDao.addAluno(aluno));
    }

    public List<AlunoDtoResponse> ListarTodos() {
        return alunoMapper.toListResponse(alunoDao.getAlunos());
    }

    public AlunoDtoResponse BuscarPorId(Integer id) {
        return alunoMapper.toResponse(alunoDao.getAlunoById(id));
    }

    public List<AlunoDtoResponse> BurcarPorNome(String nome) {
        return alunoMapper.toListResponse(alunoDao.getAlunoByName(nome));
    }

    public AlunoDtoResponse alterar(Integer id, AlunoDtoRequest aluno) {
        return alunoMapper.toResponse(alunoDao.alterarAluno(id,alunoMapper.toEntity(aluno)));
    }

    public AlunoDtoResponse excluir(Integer id) {
        return alunoMapper.toResponse(alunoDao.excluirAluno(id));
    }
}
