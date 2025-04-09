package com.calculadora.calculadora_crud.service;

import com.calculadora.calculadora_crud.model.Historico;
import com.calculadora.calculadora_crud.repository.HistoricoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HistoricoService {
    private final HistoricoRepository historicoRepository;

    public HistoricoService(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    @Transactional
    public Historico salvarCalculo(String expressao, Float resultado) {
        Historico historico = new Historico(expressao, resultado);
        return historicoRepository.save(historico);
    }

    @Transactional(readOnly = true)
    public List<Historico> obterHistoricoCompleto() {
        return historicoRepository.findAllByOrderByDataCriacaoDesc();
    }

    @Transactional
    public void limparHistorico() {
        historicoRepository.deleteAll();
    }
}