package com.study.dto;

import com.study.enums.SEXO;
import com.study.enums.TITULO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDtoResponse {
    private Integer id;
    private String nome;
    private SEXO sexo;
    private TITULO titulo;
}
