package com.calculadora.calculadora_crud.util;

import java.util.Scanner;

public class ConsoleIO {
    private static final Scanner scanner = new Scanner(System.in);

    public static String lerExpressao() {
        System.out.print(MensagensConstantes.DIGITE_EXPRESSAO);
        return scanner.nextLine().trim();
    }

    public static void exibirResultado(float resultado) {
        System.out.printf("%s %.2f\n", MensagensConstantes.RESULTADO, resultado);
    }

    public static void exibirErro(String mensagem) {
        System.err.println("Erro: " + mensagem);
    }

    public static void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}