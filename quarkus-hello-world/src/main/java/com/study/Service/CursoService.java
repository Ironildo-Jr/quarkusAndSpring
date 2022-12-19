package com.study.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.study.dto.CursoDto;

public class CursoService {
    private HashMap<Integer, CursoDto> cursos = new HashMap<>();
    
    public CursoDto cadastrar(CursoDto curso) {
        cursos.put(curso.getId(), curso);
        return curso;
    }

    public List<CursoDto> ListarTodos(){
        return cursos.values().stream().collect(Collectors.toList());
    }

    public CursoDto BuscarPorId(Integer id){
        return cursos.get(id);
    }

    public List<CursoDto> BuscarPorNome(String nome){
        return cursos.values().stream().filter(c -> c.getNome().startsWith(nome)).collect(Collectors.toList());
    }

    public CursoDto alterar(CursoDto curso) {
        return cursos.put(curso.getId(), curso);
    }

    public CursoDto excluir(Integer id) {
        return cursos.remove(id);
    }

    public HashMap<Integer, CursoDto> getCursos() {
        return cursos;
    }
}
