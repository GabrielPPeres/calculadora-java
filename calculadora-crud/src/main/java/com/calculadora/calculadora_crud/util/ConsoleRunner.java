package com.calculadora.calculadora_crud.util;

import com.calculadora.calculadora_crud.service.CalculadoraService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final CalculadoraService calculadoraService;

    public ConsoleRunner(CalculadoraService calculadoraService) {
        this.calculadoraService = calculadoraService;
    }

    @Override
    public void run(String... args) throws Exception {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("\n=== CALCULADORA ===");
        System.out.println("Digite 'sair' para encerrar");

        while (true) {
            System.out.print("Expressão: ");
            String expressao = scanner.nextLine();

            if (expressao.equalsIgnoreCase("sair")) {
                break;
            }

            try {
                var resultado = calculadoraService.calcularExpressao(expressao);
                System.out.println("Resultado: " + resultado.getResultado());
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        scanner.close();
        System.exit(0);
    }
}