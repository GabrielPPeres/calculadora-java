package com.calculadora.calculadora_crud.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_calculos")
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String expressao;

    @Column(nullable = false)
    private Float resultado;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    public Historico() {
        this.dataCriacao = LocalDateTime.now();
    }

    public Historico(String expressao, Float resultado) {
        this();
        this.expressao = expressao;
        this.resultado = resultado;
    }

    public Long getId() {
        return id;
    }

    public String getExpressao() {
        return expressao;
    }

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }

    public Float getResultado() {
        return resultado;
    }

    public void setResultado(Float resultado) {
        this.resultado = resultado;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}