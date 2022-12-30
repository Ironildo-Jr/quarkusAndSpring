package com.study.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.study.enums.SEXO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AlunoDtoRequest {

    @NotBlank(message = "Matricula nao pode ser nula ou vazia")
    @Size(min = 8 , message = "Matricula deve ter pelo menos 8 digitos")
    @Size(max = 8 , message = "Matricula deve ter no maximo 8 digitos")
    private String matricula;

    @NotBlank(message = "Nome nao pode ser nulo ou vazio")
    @Size(min = 2 , message = "Nome deve ter pelo menos 2 digitos")
    private String nome;

    private SEXO sexo;
}
