package com.calculadora.calculadora_crud.service.validation;

import com.calculadora.calculadora_crud.util.MensagensConstantes;
import org.springframework.stereotype.Service;

@Service
public class InputValidator {

    public void validarExpressao(String expressao) throws IllegalArgumentException {
        if (expressao == null || expressao.trim().isEmpty()) {
            throw new IllegalArgumentException(MensagensConstantes.ERRO_EXPRESSAO_INVALIDA);
        }

        if (!expressao.matches("^[0-9+\\-*/., ]+$")) {
            throw new IllegalArgumentException(MensagensConstantes.ERRO_CARACTERES_INVALIDOS);
        }

        if (expressao.matches(".*[+\\-*/]$")) {
            throw new IllegalArgumentException(MensagensConstantes.ERRO_EXPRESSAO_INVALIDA);
        }

        if (expressao.matches(".*[+\\-*/][+\\-*/].*")) {
            throw new IllegalArgumentException(MensagensConstantes.ERRO_OPERADORES_DUPLICADOS);
        }
    }

    public void validarNumeros(float[] numeros) {
        if (numeros == null || numeros.length == 0) {
            throw new IllegalArgumentException("Nenhum número encontrado na expressão");
        }
        for (float numero : numeros) {

            if (Float.isNaN(numero) || Float.isInfinite(numero)) {
                throw new IllegalArgumentException("A expressão contém números inválidos");
            }
        }
    }

    public void verificarDivisaoPorZero(String expressao) throws ArithmeticException {
        String expressaoNormalizada = expressao.replace(',', '.');

        if (expressaoNormalizada.matches(".*/(0(\\.0*)?)[^0-9].*") ||
                expressaoNormalizada.matches(".*/(0(\\.0*)?)$")) {
            throw new ArithmeticException(MensagensConstantes.ERRO_DIVISAO_ZERO);
        }

    }
}