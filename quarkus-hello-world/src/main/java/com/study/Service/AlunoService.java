package com.study.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.study.Resource.AlunoResource;
import com.study.dto.AlunoDto;

public class AlunoService {
    private final Logger log = LoggerFactory.getLogger(AlunoResource.class);
    private HashMap<Integer, AlunoDto> alunos = new HashMap<>();

    public AlunoDto cadastrar(AlunoDto aluno) {
        log.info(String.format("Aluno %d Cadastrado", aluno.getId()));
        alunos.put(aluno.getId(), aluno);
        return aluno;
    }

    public List<AlunoDto> ListarTodos() {
        return alunos.values().stream().collect(Collectors.toList());
    }

    public AlunoDto BuscarPorId(Integer id) {
        return alunos.get(id);
    }

    public List<AlunoDto> BurcarPorNome(String nome) {
        return alunos.values().stream().filter(a -> a.getNome().startsWith(nome))
                .collect(Collectors.toList());
    }

    public AlunoDto alterar(AlunoDto aluno) {
        return alunos.put(aluno.getId(), aluno);
    }

    public AlunoDto excluir(Integer id) {
        return alunos.remove(id);
    }
}
