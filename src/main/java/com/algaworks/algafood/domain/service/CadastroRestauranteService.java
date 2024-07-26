package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public Restaurante salvar(Restaurante restaurante) {
        if(restaurante.getCozinha().getId() != null){
            throw new IllegalArgumentException("Cozinha não pode ser nula");
        }
        return restauranteRepository.save(restaurante);
    }

    public void remover(Restaurante restauranteId) {
        restauranteRepository.deleteById(restauranteId.getId());
    }
}
