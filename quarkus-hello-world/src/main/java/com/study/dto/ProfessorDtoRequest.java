package com.study.dto;

import com.study.enums.SEXO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDtoRequest {
    private String nome;
    private SEXO sexo;
    private String titulo;
}
