package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    @GetMapping("/{cidadeId}")
    public Cidade buscar(Long cidadeId) {
        return cidadeRepository.buscar(cidadeId);
    }

    @PostMapping
    public ResponseEntity<Cidade> adicionar(@RequestBody Cidade cidade) {
        cadastroCidade.salvar(cidade);
        return ResponseEntity.status(201).body(cidade);
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
        Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        return ResponseEntity.ok(cidadeAtual);
    }

    @DeleteMapping("/{cidadeId}")
    public void remover(@PathVariable Long cidadeId) {
        Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);
        if(cidadeAtual != null) {
            cadastroCidade.remover(cidadeAtual);
        }
    }
}
