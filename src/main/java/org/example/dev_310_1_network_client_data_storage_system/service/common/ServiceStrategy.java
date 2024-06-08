package org.example.dev_310_1_network_client_data_storage_system.service.common;
import org.example.dev_310_1_network_client_data_storage_system.common.exceptions.NoObjectException;

import java.util.Optional;
import java.util.stream.Stream;

public interface ServiceStrategy<T, U> {
    Optional<T> create(T entity);
    void remove(U key) throws NoObjectException, UnknownError;
    void update(T dto) throws NoObjectException, UnknownError;
    Stream<T> findAll();
    Optional<T> findByKey(U arg);
}
