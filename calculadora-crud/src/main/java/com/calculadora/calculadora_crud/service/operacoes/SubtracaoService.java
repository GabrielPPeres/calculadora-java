package com.calculadora.calculadora_crud.service.operacoes;

import org.springframework.stereotype.Service;

@Service
public class SubtracaoService {
    public float calcular(float a, float b) {
        return a - b;
    }
}
