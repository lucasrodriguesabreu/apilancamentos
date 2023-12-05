package com.lucas.services;

import com.lucas.domains.Lancamento;
import com.lucas.domains.LancamentoDTO;
import com.lucas.repositories.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;

    @Autowired
    public LancamentoService(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    public Lancamento insert(LancamentoDTO lancamentoDTO) {
        Lancamento novoLancamento = new Lancamento();
        novoLancamento.setId(lancamentoDTO.getId());
        novoLancamento.setVencimento(lancamentoDTO.getVencimento());
        novoLancamento.setValor(lancamentoDTO.getValor());
        novoLancamento.setDescricao(lancamentoDTO.getDescricao());
        novoLancamento.setPago(lancamentoDTO.getPago());
        return lancamentoRepository.save(novoLancamento);
    }

    public List<Lancamento> findLancamentos() {
        return lancamentoRepository.findAll();
    }

    public Optional<Lancamento> findById(String id) {
        return lancamentoRepository.findById(id);
    }

    public Lancamento updateLancamento(String id, LancamentoDTO novoLancamentoDTO) {
        Optional<Lancamento> pagamentoExistente = lancamentoRepository.findById(id);

        if (pagamentoExistente.isPresent()) {
            Lancamento lancamento = pagamentoExistente.get();
            lancamento.setVencimento(novoLancamentoDTO.getVencimento());
            lancamento.setValor(novoLancamentoDTO.getValor());
            lancamento.setDescricao(novoLancamentoDTO.getDescricao());
            lancamento.setPago(novoLancamentoDTO.getPago());

            return lancamentoRepository.save(lancamento);
        }
        return null;
    }

    public boolean deletePagamento(String id) {
        if (lancamentoRepository.existsById(id)) {
            lancamentoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
