package com.algaworks.algafood.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cidade {

        @EqualsAndHashCode.Include
        @Id
        private Long id;

        private String nome;

        @ManyToOne
        private Estado estado;
}
