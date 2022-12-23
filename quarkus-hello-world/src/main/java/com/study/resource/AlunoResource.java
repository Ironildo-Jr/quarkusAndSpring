package com.study.resource;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
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

import com.study.dto.AlunoDtoRequest;
import com.study.dto.AlunoDtoResponse;
import com.study.dto.ErrorResponseDto;
import com.study.service.AlunoService;

@RequestScoped
@Path("/aluno")
public class AlunoResource {
    @Inject
    AlunoService serviceAluno;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarAluno(AlunoDtoRequest aluno) {
        try {
            serviceAluno.cadastrar(aluno);
            return Response.status(Response.Status.CREATED).build();
        } catch (ConstraintViolationException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ErrorResponseDto.createFromValidation(exception))
                    .build();
        }
    }

    public Response listarAlunos() {
        return Response.ok(serviceAluno.listarTodos()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarAluno(@PathParam("id") Integer id) {
        AlunoDtoResponse aluno = serviceAluno.buscarPorId(id);
        if (Objects.isNull(aluno))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(aluno).build();
    }

    @GET
    public Response buscarAlunoPorNome(@QueryParam("Nome") String nome) {
        if (nome == null)
            return listarAlunos();
        List<AlunoDtoResponse> alunosFiltrados = serviceAluno.burcarPorNome(nome);
        if (Objects.isNull(alunosFiltrados))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(alunosFiltrados).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response alterarAluno(@PathParam("id") Integer id, AlunoDtoRequest aluno) {
        try {
            AlunoDtoResponse alunoAlterado = serviceAluno.alterar(id, aluno);
            if (Objects.isNull(alunoAlterado))
                return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(alunoAlterado).build();
        } catch (ConstraintViolationException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ErrorResponseDto.createFromValidation(exception))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarAluno(@PathParam("id") Integer id) {
        if (serviceAluno.excluir(id))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}