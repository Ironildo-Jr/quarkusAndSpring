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

import com.study.dto.ProfessorDto;

@Path("/professor")
public class ProfessorResource {
    private final Logger log = LoggerFactory.getLogger(ProfessorResource.class);
    private HashMap<Integer, ProfessorDto> professores = new HashMap<>();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrandoProfessor(ProfessorDto professor) {
        log.info(String.format("professor %d Cadastrado", professor.getId()));
        professores.put(professor.getId(), professor);
        return Response.status(Response.Status.CREATED).build();
    }

    public Response listarProfessors() {
        return Response.ok(Arrays.asList(professores.values())).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarProfessor(@PathParam("id") Integer id) {
        ProfessorDto professor = professores.get(id);
        if (Objects.isNull(professor))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(professor).build();
    }

    @GET
    public Response buscarProfessorPorNome(@QueryParam("prefixo") String nome) {
        if(nome == null) return listarProfessors();
        List<ProfessorDto> ProfessorsFiltrados = professores.values().stream().filter(a -> a.getNome().startsWith(nome)).collect(Collectors.toList());
        if (Objects.isNull(ProfessorsFiltrados))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(ProfessorsFiltrados).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarProfessor(ProfessorDto professor) {
        if (Objects.isNull(professores.get(professor.getId())))
            return Response.status(Response.Status.NOT_FOUND).build();
        professores.put(professor.getId(), professor);
        return Response.ok(professor).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarProfessor(@PathParam("id") Integer id) {
        ProfessorDto professor = professores.get(id);
        if (Objects.isNull(professor))
            return Response.status(Response.Status.NOT_FOUND).build();
        professores.remove(professor.getId());
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
