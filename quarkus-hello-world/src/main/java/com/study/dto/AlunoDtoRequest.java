package com.study.dto;

public class AlunoDtoRequest {
    private String nome;

    public AlunoDtoRequest(String nome) {
        this.nome = nome;
    }

    public AlunoDtoRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
