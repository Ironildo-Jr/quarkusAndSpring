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

import com.study.dto.CursoDto;


@Path("/curso")
public class CursoResource {
    private HashMap<Integer, CursoDto> cursos = new HashMap<>();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrandoCurso(CursoDto curso) {
        cursos.put(curso.getId(), curso);
        return Response.status(Response.Status.CREATED).build();
    }


    public Response listarCursos() {
        return Response.ok(Arrays.asList(cursos.values())).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarCurso(@PathParam("id") Integer id) {
        CursoDto curso = cursos.get(id);
        if (Objects.isNull(curso))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(curso).build();
    }

    @GET
    public Response buscarCursoPorNome(@QueryParam("prefixo") String nome) {
        if(nome == null) return listarCursos();
        List<CursoDto> cursosFiltrados = cursos.values().stream().filter(c -> c.getNome().startsWith(nome)).collect(Collectors.toList());
        if (Objects.isNull(cursosFiltrados))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(cursosFiltrados).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarCurso(CursoDto curso) {
        if (Objects.isNull(cursos.get(curso.getId())))
            return Response.status(Response.Status.NOT_FOUND).build();
        cursos.put(curso.getId(), curso);
        return Response.ok(curso).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarCurso(@PathParam("id") Integer id) {
        CursoDto curso = cursos.get(id);
        if (Objects.isNull(curso))
            return Response.status(Response.Status.NOT_FOUND).build();
        cursos.remove(curso.getId());
        return Response.status(Response.Status.NO_CONTENT).build();    
    }
}
