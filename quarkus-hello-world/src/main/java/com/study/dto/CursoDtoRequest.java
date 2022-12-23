package com.study.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDtoRequest {

    @NotBlank(message = "Nome nao pode ser nula")
    @Size(min = 2, message = "Nome deve ter pelo menos 2 digitos")
    private String nome;

    @NotBlank(message = "Descricao nao pode ser nula")
    @Size(min = 10, message = "Descricao deve ter pelo menos 10 digitos")
    private String descricao;

    @NotBlank(message = "Duracao nao pode ser nula")
    @Size(min = 1 , message = "Duracao deve ter pelo menos 1 digitos")
    private int duracao;
}
