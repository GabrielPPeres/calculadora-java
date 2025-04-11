package com.calculadora.calculadora_crud;

import com.calculadora.calculadora_crud.exception.ExpressaoInvalidaException;
import com.calculadora.calculadora_crud.exception.OperacaoInvalidaException;
import com.calculadora.calculadora_crud.model.Historico;
import com.calculadora.calculadora_crud.service.CalculadoraService;
import com.calculadora.calculadora_crud.service.HistoricoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final CalculadoraService calculadoraService;
    private final HistoricoService historicoService;

    public ConsoleRunner(CalculadoraService calculadoraService, HistoricoService historicoService) {
        this.calculadoraService = calculadoraService;
        this.historicoService = historicoService;
    }

    @Override
    public void run(String... args) throws Exception {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("\n=== CALCULADORA ===");
        System.out.println("Comandos disponíveis:");
        System.out.println("  'hist' - Exibir histórico de cálculos");
        System.out.println("  'limpar' - Limpar histórico");
        System.out.println("  'sair' - Encerrar programa");
        System.out.println("Ou digite uma expressão matemática (ex: 2+2)");

        while (true) {
            System.out.print("\nComando ou expressão: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("sair")) {
                break;
            } else if (input.equalsIgnoreCase("hist")) {
                exibirHistorico();
            } else if (input.equalsIgnoreCase("limpar")) {
                historicoService.limparHistorico();
                System.out.println("Histórico limpo com sucesso!");
            } else {
                try {
                    var resultado = calculadoraService.calcularExpressao(input);
                    System.out.println("Resultado: " + resultado.getResultadoFormatado());


                    if (resultado.getMensagem() != null &&
                            !resultado.getMensagem().isEmpty() &&
                            !resultado.getMensagem().startsWith("Erro")) {
                        System.out.println(resultado.getMensagem());
                    }
                } catch (ExpressaoInvalidaException | OperacaoInvalidaException | ArithmeticException e) {
                    System.out.println("Erro: " + getMensagemErroRefinada(e));
                } catch (Exception e) {
                    System.out.println("Erro inesperado: " + getMensagemErroRefinada(e));
                }
            }
        }

        scanner.close();
        System.exit(0);
    }

    private String getMensagemErroRefinada(Exception e) {
        String mensagemErro = e.getMessage();
        if (e.getCause() != null) {
            mensagemErro = e.getCause().getMessage();
        }
        return mensagemErro;
    }

    private void exibirHistorico() {
        List<Historico> historico = historicoService.obterHistoricoCompleto();

        if (historico.isEmpty()) {
            System.out.println("O histórico está vazio.");
            return;
        }

        System.out.println("\n=== HISTÓRICO DE CÁLCULOS ===");
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

        for (int i = 0; i < historico.size(); i++) {
            Historico h = historico.get(i);
            System.out.printf("%d. %s = %s (%s)\n",
                    i + 1,
                    h.getExpressao(),
                    nf.format(h.getResultado()),
                    h.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        }
    }
}