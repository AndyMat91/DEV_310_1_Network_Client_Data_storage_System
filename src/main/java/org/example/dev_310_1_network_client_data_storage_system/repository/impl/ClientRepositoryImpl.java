package org.example.dev_310_1_network_client_data_storage_system.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.dev_310_1_network_client_data_storage_system.common.entity.ClientEntity;
import org.example.dev_310_1_network_client_data_storage_system.common.exceptions.NoObjectException;
import org.example.dev_310_1_network_client_data_storage_system.repository.ClientRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Stream<ClientEntity> findAll() {
        return em.createNativeQuery("select * from clients", ClientEntity.class).getResultList().stream();
    }

    @Override
    public Optional<ClientEntity> findClientById(Long id) {
        return Optional.of(id).map(obj -> em.find(ClientEntity.class, obj));
    }

    @Override
    @Transactional
    public void remove(Long id) throws NoObjectException, UnknownError {
        findClientById(id).ifPresent(client -> em.remove(client));
    }

    @Override
    @Transactional
    public void update(ClientEntity client) throws NoObjectException, UnknownError {
        findClientById(client.getClientId()).ifPresentOrElse(
                entity -> {
                    entity.setClientName(client.getClientName());
                    entity.setClientType(client.getClientType());
                    entity.setDatereg(client.getDatereg());
                    em.merge(entity);
                    em.flush();
                },
                () -> {
                    throw new NoObjectException(String.format("Элементы по идентификатору '%d' не найдены", client.getClientId()));
                }
        );
    }


    @Override
    @Transactional
    public Optional<ClientEntity> create(ClientEntity client) {
        em.persist(client);
        return Optional.of(client);
    }
}
