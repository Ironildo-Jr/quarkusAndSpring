package com.study.Resource;

import java.util.List;
import java.util.Objects;

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

import com.study.Service.AlunoService;
import com.study.dto.AlunoDto;

@Path("/aluno")
public class AlunoResource {
    AlunoService serviceAluno;

    AlunoResource() {
        serviceAluno = new AlunoService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrandoAluno(AlunoDto aluno) {
        serviceAluno.cadastrar(aluno);
        return Response.status(Response.Status.CREATED).build();
    }

    public Response listarAlunos() {
        return Response.ok(serviceAluno.ListarTodos()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarAluno(@PathParam("id") Integer id) {
        AlunoDto aluno = serviceAluno.BuscarPorId(id);
        if (Objects.isNull(aluno))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(aluno).build();
    }

    @GET
    public Response buscarAlunoPorNome(@QueryParam("prefixo") String nome) {
        if (nome == null)
            return listarAlunos();
        List<AlunoDto> alunosFiltrados = serviceAluno.BurcarPorNome(nome);
        if (Objects.isNull(alunosFiltrados))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(alunosFiltrados).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarAluno(AlunoDto aluno) {
        AlunoDto alunoAlterado = serviceAluno.alterar(aluno);
        if (Objects.isNull(alunoAlterado))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(alunoAlterado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarAluno(@PathParam("id") Integer id) {
        AlunoDto aluno = serviceAluno.excluir(id);
        if (Objects.isNull(aluno))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}