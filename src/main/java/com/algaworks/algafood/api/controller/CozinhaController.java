package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import java.util.List;


@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    @GetMapping("/{cozinhaId}")
    public Cozinha buscar(@PathVariable Long cozinhaId) {
        return cozinhaRepository.buscar(cozinhaId);
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        cadastroCozinha.salvar(cozinha);
        return ResponseEntity.status(201).body(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);
        return ResponseEntity.ok(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    public void remover(@PathVariable Long cozinhaId) {
        Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
        if(cozinhaAtual != null) {
            cadastroCozinha.remover(cozinhaAtual);
        }
    }
}
