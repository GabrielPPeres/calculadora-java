package com.calculadora.calculadora_crud.service.parser;

public enum PrioridadeOperacao {
    ALTA(2, new char[]{'*', '/'}),
    BAIXA(1, new char[]{'+', '-'});

    private final int prioridade;
    private final char[] operadores;

    PrioridadeOperacao(int prioridade, char[] operadores) {
        this.prioridade = prioridade;
        this.operadores = operadores;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public char[] getOperadores() {
        return operadores;
    }

    public static PrioridadeOperacao de(char operador) {
        for (PrioridadeOperacao po : values()) {
            for (char op : po.getOperadores()) {
                if (op == operador) {
                    return po;
                }
            }
        }
        throw new IllegalArgumentException("Operador desconhecido: " + operador);
    }
}