package com.calculadora.calculadora_crud.dto;

import java.text.NumberFormat;
import java.util.Locale;

public class ResultadoDTO {
    private float resultado;
    private String resultadoFormatado;
    private String mensagem;

    public ResultadoDTO(float resultado, String mensagem) {
        this.resultado = resultado;
        this.mensagem = mensagem;

        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        this.resultadoFormatado = nf.format(resultado);
    }

    public float getResultado() {
        return resultado;
    }

    public String getResultadoFormatado() {
        return resultadoFormatado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setResultado(float resultado) {
        this.resultado = resultado;

        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        this.resultadoFormatado = nf.format(resultado);
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}