package com.study.repository;

import javax.enterprise.context.ApplicationScoped;

import com.study.model.Professor;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ProfessorRepository implements PanacheRepositoryBase<Professor, Integer> {
}
