package com.ramonthalison.delivery.cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente {

    // O banco gera o identificador único de cada cliente.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Dados mantidos enxutos para preservar o escopo de um projeto de estágio.
    private String nome;
    private String cep;

    // Construtor sem argumentos exigido pelo JPA.
    protected Cliente() {
    }

    public Cliente(String nome, String cep) {
        this.nome = nome;
        this.cep = cep;
    }

    // Atualiza somente os campos permitidos pela regra de negócio.
    public void atualizar(String nome, String cep) {
        this.nome = nome;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCep() {
        return cep;
    }
}
