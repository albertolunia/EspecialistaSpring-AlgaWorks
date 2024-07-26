package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);

        return ResponseEntity.ok(cozinha.get());
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        cadastroCozinha.salvar(cozinha);
        return ResponseEntity.status(201).body(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);

        BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");

        Cozinha cozinhaSalva = cadastroCozinha.salvar(cozinhaAtual.get());
        return ResponseEntity.ok(cozinhaSalva);
    }

    @DeleteMapping("/{cozinhaId}")
    public void remover(@PathVariable Long cozinhaId) {
        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);

        Cozinha cozinhaSalva = cadastroCozinha.salvar(cozinhaAtual.get());
        cadastroCozinha.remover(cozinhaSalva);
    }
}
