package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(Long cidadeId) {
        Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);

        return ResponseEntity.ok(cidade.get());
    }

    @PostMapping
    public ResponseEntity<Cidade> adicionar(@RequestBody Cidade cidade) {
        cadastroCidade.salvar(cidade);
        return ResponseEntity.status(201).body(cidade);
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
        Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);

        BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");

        Cidade cidadeSalva = cadastroCidade.salvar(cidadeAtual.get());
        return ResponseEntity.ok(cidadeSalva);
    }

    @DeleteMapping("/{cidadeId}")
    public void remover(@PathVariable Long cidadeId) {
        Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);

        Cidade cidadeSalva = cadastroCidade.salvar(cidadeAtual.get());
        cadastroCidade.remover(cidadeSalva);
    }
}
