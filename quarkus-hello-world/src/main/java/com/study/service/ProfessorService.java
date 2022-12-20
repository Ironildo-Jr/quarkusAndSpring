package com.study.service;

import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;

import com.study.dto.ProfessorDto;

@ApplicationScoped
public class ProfessorService {

    private HashMap<Integer, ProfessorDto> professores = new HashMap<>();

    public void cadastrandoProfessor(ProfessorDto professor){
        professores.put(professor.getId(), professor);
    }
}
