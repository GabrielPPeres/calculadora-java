package com.calculadora.calculadora_crud.service.parser;

import org.springframework.stereotype.Service;
import com.calculadora.calculadora_crud.service.validation.InputValidator;

@Service
public class ExpressaoParser {
    private final InputValidator validator;

    public ExpressaoParser(InputValidator validator) {
        this.validator = validator;
    }

    public float[] extrairNumeros(String expressao) {
        String expressaoNormalizada = expressao.replace(',', '.');
        String[] partes = expressaoNormalizada.split("[-+*/]");
        float[] numeros = new float[partes.length];

        for (int i = 0; i < partes.length; i++) {
            numeros[i] = Float.parseFloat(partes[i].trim());
        }
        validator.validarNumeros(numeros);
        return numeros;
    }

    public char[] extrairOperadores(String expressao) {
        String operadores = expressao.replaceAll("[^+\\-*/]", "");
        return operadores.toCharArray();
    }
}