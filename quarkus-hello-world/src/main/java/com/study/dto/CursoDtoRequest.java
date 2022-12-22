package com.study.dto;

public class CursoDtoRequest {
    private String nome;

    public CursoDtoRequest(String nome) {
        this.nome = nome;
    }

    public CursoDtoRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
