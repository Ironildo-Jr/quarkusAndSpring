package com.study.resource;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.study.dto.AlunoDtoResponse;
import com.study.dto.ErrorResponseDto;
import com.study.dto.ProfessorDtoRequest;
import com.study.dto.ProfessorDtoResponse;
import com.study.service.ProfessorService;

@RequestScoped
@Path("/professor")
public class ProfessorResource {

    @Inject
    ProfessorService professorService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarProfessor(ProfessorDtoRequest professor) {
        try {
            professorService.cadastrar(professor);
            return Response.status(Response.Status.CREATED).build();
        } catch (ConstraintViolationException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ErrorResponseDto.createFromValidation(exception))
                    .build();
        }
    }

    public Response listarProfessors() {
        return Response.ok(professorService.listarTodos()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarProfessor(@PathParam("id") Integer id) {
        ProfessorDtoResponse professor = professorService.buscarPorId(id);
        if (Objects.isNull(professor))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(professor).build();
    }

    @GET
    public Response buscarProfessorPorNome(@QueryParam("Nome") String nome) {
        if (nome == null)
            return listarProfessors();
        List<ProfessorDtoResponse> professoresFiltrados = professorService.burcarPorNome(nome);
        if (Objects.isNull(professoresFiltrados))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(professoresFiltrados).build();
    }

    @GET
    @Path("/{id}/alunos")
    public Response buscarAlunosProfessor(@PathParam("id") Integer id) {
        List<AlunoDtoResponse> alunos = professorService.buscarAlunosProfessor(id);
        if (Objects.isNull(alunos))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(alunos).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response alterarProfessor(@PathParam("id") Integer id, ProfessorDtoRequest professor) {
        try {
            ProfessorDtoResponse professorAlterado = professorService.alterar(id, professor);
            if (Objects.isNull(professorAlterado))
                return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(professorAlterado).build();
        } catch (ConstraintViolationException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ErrorResponseDto.createFromValidation(exception))
                    .build();
        }
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id-professor}/curso/{id-curso}")
    public Response alterarCursoProfessor(@PathParam("id-professor") Integer idProfessor,
            @PathParam("id-curso") Integer idCurso) {
        ProfessorDtoResponse professorAlterado = professorService.alterarCurso(idProfessor, idCurso);
        if (Objects.isNull(professorAlterado))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(professorAlterado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirProfessor(@PathParam("id") Integer id) {
        if (professorService.excluir(id))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
