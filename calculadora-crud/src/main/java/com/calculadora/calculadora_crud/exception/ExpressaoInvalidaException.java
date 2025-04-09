package com.calculadora.calculadora_crud.exception;

public class ExpressaoInvalidaException extends RuntimeException {
    public ExpressaoInvalidaException(String message) {
        super(message);
    }

    public ExpressaoInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }
}