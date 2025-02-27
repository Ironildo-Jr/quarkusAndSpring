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

import com.study.dto.CursoDtoRequest;
import com.study.dto.CursoDtoResponse;
import com.study.dto.ErrorResponseDto;
import com.study.dto.ProfessorDtoResponse;
import com.study.service.CursoService;

@RequestScoped
@Path("/curso")
public class CursoResource {
    @Inject
    CursoService cursoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarCurso(CursoDtoRequest curso) {
        try {
            cursoService.cadastrar(curso);
            return Response.status(Response.Status.CREATED).build();
        } catch (ConstraintViolationException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ErrorResponseDto.createFromValidation(exception))
                    .build();
        }
    }

    public Response listarCursos() {
        return Response.ok(cursoService.listarTodos()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarCurso(@PathParam("id") Integer id) {
        CursoDtoResponse curso = cursoService.buscarPorId(id);
        if (Objects.isNull(curso))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(curso).build();
    }

    @GET
    @Path("/{id}/professor")
    public Response buscarProfessorCurso(@PathParam("id") Integer id) {
        ProfessorDtoResponse professor = cursoService.buscarProfessorCurso(id);
        if (Objects.isNull(professor))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(professor).build();
    }

    @GET
    public Response buscarCursoPorNome(@QueryParam("Nome") String nome) {
        if (nome == null)
            return listarCursos();
        List<CursoDtoResponse> cursosFiltrados = cursoService.burcarPorNome(nome);
        if (Objects.isNull(cursosFiltrados))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(cursosFiltrados).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response alterarCurso(@PathParam("id") Integer id, CursoDtoRequest curso) {
        try {
            CursoDtoResponse cursoAlterado = cursoService.alterar(id, curso);
            if (Objects.isNull(cursoAlterado))
                return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(cursoAlterado).build();
        } catch (ConstraintViolationException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ErrorResponseDto.createFromValidation(exception))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarCurso(@PathParam("id") Integer id) {
        if (cursoService.excluir(id))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
