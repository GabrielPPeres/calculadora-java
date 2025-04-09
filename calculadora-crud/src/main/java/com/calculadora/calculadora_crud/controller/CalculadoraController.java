package com.calculadora.calculadora_crud.controller;

import com.calculadora.calculadora_crud.dto.ExpressaoDTO;
import com.calculadora.calculadora_crud.dto.ResultadoDTO;
import com.calculadora.calculadora_crud.service.CalculadoraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculadora")
public class CalculadoraController {

    private final CalculadoraService calculadoraService;

    public CalculadoraController(CalculadoraService calculadoraService) {
        this.calculadoraService = calculadoraService;
    }

    @PostMapping("/calcular")
    public ResponseEntity<ResultadoDTO> calcular(@RequestBody ExpressaoDTO expressaoDTO) {
        ResultadoDTO resultado = calculadoraService.calcularExpressao(expressaoDTO.getExpressao());
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/calcular-console")
    public ResponseEntity<ResultadoDTO> calcularConsole(@RequestParam String expressao) {
        ResultadoDTO resultado = calculadoraService.calcularExpressao(expressao);
        return ResponseEntity.ok(resultado);
    }
}