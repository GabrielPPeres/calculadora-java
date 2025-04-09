package com.calculadora.calculadora_crud.controller;

import com.calculadora.calculadora_crud.model.Historico;
import com.calculadora.calculadora_crud.service.HistoricoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historico")
public class HistoricoController {

    private final HistoricoService historicoService;

    public HistoricoController(HistoricoService historicoService) {
        this.historicoService = historicoService;
    }

    @GetMapping
    public ResponseEntity<List<Historico>> obterHistorico() {
        return ResponseEntity.ok(historicoService.obterHistoricoCompleto());
    }

    @DeleteMapping
    public ResponseEntity<Void> limparHistorico() {
        historicoService.limparHistorico();
        return ResponseEntity.noContent().build();
    }
}