package com.calculadora.calculadora_crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ExpressaoDTO {
    @NotBlank(message = "A expressão não pode estar vazia")
    @Pattern(regexp = "^[0-9+\\-*/., ]+$", message = "Expressão contém caracteres inválidos")
    private String expressao;

    public ExpressaoDTO() {}

    public ExpressaoDTO(String expressao) {
        this.expressao = expressao;
    }

    public String getExpressao() {
        return expressao;
    }

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }
}