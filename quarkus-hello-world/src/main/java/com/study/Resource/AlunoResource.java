package com.study.Resource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.study.dto.AlunoDto;

@Path("/aluno")
public class AlunoResource {
    private final Logger log = LoggerFactory.getLogger(AlunoResource.class);
    private HashMap<Integer, AlunoDto> alunos = new HashMap<>();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrandoAluno(AlunoDto aluno) {
        log.info(String.format("Aluno %d Cadastrado", aluno.getId()));
        alunos.put(aluno.getId(), aluno);
        return Response.status(Response.Status.CREATED).build();
    }

    public Response listarAlunos() {
        return Response.ok(Arrays.asList(alunos.values())).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarAluno(@PathParam("id") Integer id) {
        AlunoDto aluno = alunos.get(id);
        if (Objects.isNull(aluno))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(aluno).build();
    }

    @GET
    public Response buscarAlunoPorNome(@QueryParam("prefixo") String nome) {
        if(nome == null) return listarAlunos();
        List<AlunoDto> alunosFiltrados = alunos.values().stream().filter(a -> a.getNome().startsWith(nome)).collect(Collectors.toList());
        if (Objects.isNull(alunosFiltrados))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(alunosFiltrados).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarAluno(AlunoDto aluno) {
        if (Objects.isNull(alunos.get(aluno.getId())))
            return Response.status(Response.Status.NOT_FOUND).build();
        alunos.put(aluno.getId(), aluno);
        return Response.ok(aluno).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarAluno(@PathParam("id") Integer id) {
        AlunoDto aluno = alunos.get(id);
        if (Objects.isNull(aluno))
            return Response.status(Response.Status.NOT_FOUND).build();
        alunos.remove(aluno.getId());
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    
}