package com.calculadora.calculadora_crud.service;

import com.calculadora.calculadora_crud.exception.ExpressaoInvalidaException;
import com.calculadora.calculadora_crud.exception.OperacaoInvalidaException;
import com.calculadora.calculadora_crud.dto.ResultadoDTO;
import com.calculadora.calculadora_crud.service.operacoes.*;
import com.calculadora.calculadora_crud.service.parser.ExpressaoParser;
import com.calculadora.calculadora_crud.service.parser.PrioridadeOperacao;
import com.calculadora.calculadora_crud.service.validation.InputValidator;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class CalculadoraService {
    private final ExpressaoParser parser;
    private final InputValidator validator;
    private final HistoricoService historicoService;
    private final SomaService adicaoService;
    private final SubtracaoService subtracaoService;
    private final MultiplicacaoService multiplicacaoService;
    private final DivisaoService divisaoService;

    public CalculadoraService(ExpressaoParser parser,
                              InputValidator validator,
                              HistoricoService historicoService,
                              SomaService adicaoService,
                              SubtracaoService subtracaoService,
                              MultiplicacaoService multiplicacaoService,
                              DivisaoService divisaoService) {
        this.parser = parser;
        this.validator = validator;
        this.historicoService = historicoService;
        this.adicaoService = adicaoService;
        this.subtracaoService = subtracaoService;
        this.multiplicacaoService = multiplicacaoService;
        this.divisaoService = divisaoService;
    }

    public ResultadoDTO calcularExpressao(String expressao) {

        expressao = expressao.trim().replaceAll("\\s+", "");
        validator.validarExpressao(expressao);
        validator.verificarDivisaoPorZero(expressao);

        float[] numeros = parser.extrairNumeros(expressao);
        char[] operadores = parser.extrairOperadores(expressao);

        float resultado = calcularComPrioridade(numeros, operadores);
        historicoService.salvarCalculo(expressao, resultado);

        return new ResultadoDTO(resultado, "Cálculo realizado com sucesso");
    }

    private float calcularComPrioridade(float[] numeros, char[] operadores) {
        Stack<Float> numerosStack = new Stack<>();
        Stack<Character> operadoresStack = new Stack<>();

        numerosStack.push(numeros[0]);

        for (int i = 0; i < operadores.length; i++) {
            char operadorAtual = operadores[i];
            float numeroAtual = numeros[i + 1];

            while (!operadoresStack.isEmpty() &&
                    PrioridadeOperacao.de(operadorAtual).getPrioridade() <=
                            PrioridadeOperacao.de(operadoresStack.peek()).getPrioridade()) {
                float num2 = numerosStack.pop();
                float num1 = numerosStack.pop();
                char op = operadoresStack.pop();
                numerosStack.push(aplicarOperacao(num1, num2, op));
            }

            operadoresStack.push(operadorAtual);
            numerosStack.push(numeroAtual);
        }

        while (!operadoresStack.isEmpty()) {
            float num2 = numerosStack.pop();
            float num1 = numerosStack.pop();
            char op = operadoresStack.pop();
            numerosStack.push(aplicarOperacao(num1, num2, op));
        }

        return numerosStack.pop();
    }

    private float aplicarOperacao(float num1, float num2, char operador) {
        return switch (operador) {
            case '+' -> adicaoService.calcular(num1, num2);
            case '-' -> subtracaoService.calcular(num1, num2);
            case '*' -> multiplicacaoService.calcular(num1, num2);
            case '/' -> divisaoService.calcular(num1, num2);
            default -> throw new OperacaoInvalidaException("Operador inválido: " + operador);
        };
    }
}