package com.lucas.controllers;

import com.lucas.domains.Lancamento;
import com.lucas.domains.LancamentoDTO;
import com.lucas.services.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/lancamentos")
public class LancamentoController {
    private final LancamentoService lancamentoService;

    @Autowired
    public LancamentoController(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }

    @PostMapping(value = "/cadastro")
    public ResponseEntity<LancamentoDTO> insert(@RequestBody LancamentoDTO lancamentoDTO) {
        Lancamento lancamentoCriado = lancamentoService.insert(lancamentoDTO);

        if(lancamentoCriado != null) {
            LancamentoDTO respostaDTO = mapToDTO(lancamentoCriado);
            System.out.println("O lançamento foi criado com sucesso!");
            return new ResponseEntity<>(respostaDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<List<Lancamento>> findLancamentos() {
        List<Lancamento> lancamentos = lancamentoService.findLancamentos();
        return new ResponseEntity<>(lancamentos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Lancamento> findById(@PathVariable String id) {
        Optional<Lancamento> lancamento = lancamentoService.findById(id);
        return lancamento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LancamentoDTO> atualizarPagamento(@PathVariable String id, @RequestBody LancamentoDTO novoLancamentoDTO) {
        Lancamento lancamentoAtualizado = lancamentoService.updateLancamento(id, novoLancamentoDTO);
        if (lancamentoAtualizado != null) {
            return new ResponseEntity<>(mapToDTO(lancamentoAtualizado), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable String id) {
        boolean deletado = lancamentoService.deletePagamento(id);
        if (deletado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private LancamentoDTO mapToDTO(Lancamento lancamento) {
        LancamentoDTO dto = new LancamentoDTO();
        dto.setId(lancamento.getId());
        dto.setVencimento(lancamento.getVencimento());
        dto.setValor(lancamento.getValor());
        dto.setDescricao(lancamento.getDescricao());
        dto.setPago(lancamento.getPago());
        return dto;
    }
}
