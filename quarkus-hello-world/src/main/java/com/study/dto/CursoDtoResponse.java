package com.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDtoResponse {
    private Integer id;
    private String nome;
    private String descricao;
    private Integer duracao;
}
