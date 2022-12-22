package com.study.resource;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
        professorService.cadastrar(professor);
        return Response.status(Response.Status.CREATED).build();
    }

    public Response listarProfessors() {
        return Response.ok(professorService.ListarTodos()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarProfessor(@PathParam("id") Integer id) {
        ProfessorDtoResponse professor = professorService.BuscarPorId(id);
        if (Objects.isNull(professor))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(professor).build();
    }

    @GET
    public Response buscarProfessorPorNome(@QueryParam("prefixo") String nome) {
        if (nome == null)
            return listarProfessors();
        List<ProfessorDtoResponse> professoresFiltrados = professorService.BurcarPorNome(nome);
        if (Objects.isNull(professoresFiltrados))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(professoresFiltrados).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response alterarProfessor(@PathParam("id") Integer id, ProfessorDtoRequest professor) {
        ProfessorDtoResponse professorAlterado = professorService.alterar(id, professor);
        if (Objects.isNull(professorAlterado))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(professorAlterado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirProfessor(@PathParam("id") Integer id) {
        ProfessorDtoResponse professor = professorService.excluir(id);
        if (Objects.isNull(professor))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
