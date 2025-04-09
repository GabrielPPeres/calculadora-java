package com.calculadora.calculadora_crud.dto;

public class ResultadoDTO {
    private float resultado;
    private String mensagem;

    public ResultadoDTO(float resultado, String mensagem) {
        this.resultado = resultado;
        this.mensagem = mensagem;
    }

    public float getResultado() {
        return resultado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setResultado(float resultado) {
        this.resultado = resultado;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}