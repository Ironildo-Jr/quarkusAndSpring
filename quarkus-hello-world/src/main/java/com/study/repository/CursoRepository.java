package com.study.repository;

import javax.enterprise.context.ApplicationScoped;

import com.study.model.Curso;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class CursoRepository implements PanacheRepositoryBase<Curso, Integer> {
}
