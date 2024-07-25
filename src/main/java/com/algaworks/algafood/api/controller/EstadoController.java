package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import java.util.List;


@RestController
@RequestMapping("/estados")
public class EstadoController {
    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId) {
        return estadoRepository.buscar(estadoId);
    }

    @PostMapping
    public ResponseEntity<Estado> adicionar(@RequestBody Estado estado) {
        cadastroEstado.salvar(estado);
        return ResponseEntity.status(201).body(estado);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
        Estado estadoAtual = estadoRepository.buscar(estadoId);

        BeanUtils.copyProperties(estado, estadoAtual, "id");

        estadoAtual = cadastroEstado.salvar(estadoAtual);
        return ResponseEntity.ok(estadoAtual);
    }

    @DeleteMapping("/{estadoId}")
    public void remover(@PathVariable Long estadoId) {
        Estado estadoAtual = estadoRepository.buscar(estadoId);
        if(estadoAtual != null) {
            cadastroEstado.remover(estadoAtual);
        }
    }
}
