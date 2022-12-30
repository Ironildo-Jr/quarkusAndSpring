package com.study.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class ProfessorDtoRequest {
    @NotBlank(message = "Nome nao pode ser nulo ou vazio")
    @Size(min = 2, message = "Nome deve ter pelo menos 2 digitos")
    private String nome;

    private SEXO sexo;

    @NotNull(message = "Titulo nao pode ser nulo")
    private TITULO titulo;
}
