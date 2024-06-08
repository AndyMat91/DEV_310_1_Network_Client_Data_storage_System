package org.example.dev_310_1_network_client_data_storage_system.repository;

import org.example.dev_310_1_network_client_data_storage_system.common.entity.ClientEntity;
import org.example.dev_310_1_network_client_data_storage_system.common.exceptions.NoObjectException;

import java.util.Optional;
import java.util.stream.Stream;

public interface ClientRepository {
    Stream<ClientEntity> findAll();

    Optional<ClientEntity> findClientById(Long id);

    void remove(Long id) throws NoObjectException, UnknownError;

    void update(ClientEntity client) throws NoObjectException, UnknownError;

    Optional<ClientEntity> create(ClientEntity client);
}
