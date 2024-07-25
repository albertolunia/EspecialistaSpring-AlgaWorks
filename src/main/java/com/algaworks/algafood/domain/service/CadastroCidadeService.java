package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade salvar(Cidade cidade) {
        if(cidade.getEstado().getId() != null){
            throw new IllegalArgumentException("Estado não pode ser nulo");
        }
        return cidadeRepository.salvar(cidade);
    }

    public void remover(Cidade cidadeId) {
        cidadeRepository.remover(cidadeId);
    }
}
