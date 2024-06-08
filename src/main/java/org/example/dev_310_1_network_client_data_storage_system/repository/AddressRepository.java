package org.example.dev_310_1_network_client_data_storage_system.repository;

import org.example.dev_310_1_network_client_data_storage_system.common.entity.AddressEntity;
import org.example.dev_310_1_network_client_data_storage_system.common.exceptions.NoObjectException;

import java.util.Optional;
import java.util.stream.Stream;

public interface AddressRepository {
    Stream<AddressEntity> findAll();

    Optional<AddressEntity> findAddressByMac(String mac);

    Stream<AddressEntity> findAddressByClientId(Long id);
    void remove(String mac) throws NoObjectException, UnknownError;

    void update(AddressEntity address) throws NoObjectException, UnknownError;

    Optional<AddressEntity> create(AddressEntity address);
}
