package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import jakarta.persistence.EntityManager;

import java.util.List;

@Component
public class PermisssaoRepositoryImpl implements PermissaoRepository {

        @PersistenceContext
        private EntityManager manager;

        @Override
        public List<Permissao> listar() {
            return manager.createQuery("from Permissao", Permissao.class)
                    .getResultList();
        }

        @Override
        public Permissao buscar(Long id) {
            return manager.find(Permissao.class, id);
        }

        @Override
        public Permissao salvar(Permissao permissao) {
            return manager.merge(permissao);
        }

        @Override
        public void remover(Permissao permissao) {
            permissao = buscar(permissao.getId());
            manager.remove(permissao);
        }
}
