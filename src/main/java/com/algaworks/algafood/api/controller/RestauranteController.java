package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    @GetMapping("/{restauranteId}")
    public Restaurante buscar(@PathVariable Long restauranteId) {
        return restauranteRepository.buscar(restauranteId);
    }

    @PostMapping
    public ResponseEntity<Restaurante> adicionar(@RequestBody Restaurante restaurante) {
        cadastroRestaurante.salvar(restaurante);
        return ResponseEntity.status(201).body(restaurante);
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
        Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

        restauranteAtual = cadastroRestaurante.salvar(restauranteAtual);

        return ResponseEntity.ok(restauranteAtual);
    }

    @DeleteMapping("/{restauranteId}")
    public void remover(@PathVariable Long restauranteId) {
        Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);
        if(restauranteAtual != null) {
            cadastroRestaurante.remover(restauranteAtual);
        }
    }
}
