package com.calculadora.calculadora_crud.exception;

public class OperacaoInvalidaException extends RuntimeException {
    public OperacaoInvalidaException(String message) {
        super(message);
    }

    public OperacaoInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }
}