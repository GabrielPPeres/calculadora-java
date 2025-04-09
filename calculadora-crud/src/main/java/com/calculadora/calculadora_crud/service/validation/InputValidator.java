package com.calculadora.calculadora_crud.service.validation;

import com.calculadora.calculadora_crud.util.MensagensConstantes;
import org.springframework.stereotype.Service;

@Service
public class InputValidator {

    public void validarExpressao(String expressao) throws IllegalArgumentException {
        if (expressao == null || expressao.trim().isEmpty()) {
            throw new IllegalArgumentException(MensagensConstantes.ERRO_EXPRESSAO_INVALIDA);
        }

        if (!expressao.matches("^[0-9+\\-*/. ]+$")) {
            throw new IllegalArgumentException(MensagensConstantes.ERRO_EXPRESSAO_INVALIDA);
        }

        if (expressao.matches(".*[+\\-*/]$")) {
            throw new IllegalArgumentException(MensagensConstantes.ERRO_EXPRESSAO_INVALIDA);
        }
    }

    public void verificarDivisaoPorZero(String expressao) throws ArithmeticException {
        if (expressao.contains("/0") && !expressao.contains("/0.")) {
            throw new ArithmeticException(MensagensConstantes.ERRO_DIVISAO_ZERO);
        }
    }

    public void validarNumeros(float[] numeros) throws NumberFormatException {
        for (float num : numeros) {
            if (Float.isInfinite(num) || Float.isNaN(num)) {
                throw new NumberFormatException(MensagensConstantes.ERRO_FORMATO_NUMERO);
            }
        }
    }
}