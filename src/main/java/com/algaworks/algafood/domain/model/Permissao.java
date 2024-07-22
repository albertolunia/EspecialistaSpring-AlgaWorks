package com.algaworks.algafood.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Permissao {

    @EqualsAndHashCode.Include
    @Id
    private Long id;

    private String nome;

    private String descricao;

}
