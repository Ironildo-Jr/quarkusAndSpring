package com.study.dao;

import java.util.ArrayList;
import java.util.List;

import com.study.model.Curso;

public class CursoDao {
    public Curso addCurso(Curso curso) {
        return new Curso();
    }

    public List<Curso> getCursos() {
        return new ArrayList<Curso>();
    }

    public Curso getCursoById(Integer id) {
        return new Curso();
    }

    public List<Curso> getCursoByName(String nome) {
        return new ArrayList<Curso>();
    }

    public Curso alterarCurso(Integer id,Curso curso){
        getCursoById(id).setNome(curso.getNome());
        return new Curso();
    }

    public Curso excluirCurso(Integer id){
        return new Curso();
    }
}
