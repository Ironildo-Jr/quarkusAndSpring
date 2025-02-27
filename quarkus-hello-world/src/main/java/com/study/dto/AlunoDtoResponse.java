package com.study.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.enums.SEXO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDtoResponse {
    private Integer id;
    private String nome;
    private SEXO sexo;
    private String matricula;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProfessorDtoResponse professor;
}
