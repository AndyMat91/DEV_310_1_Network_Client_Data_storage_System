package org.example.dev_310_1_network_client_data_storage_system.service;

import org.example.dev_310_1_network_client_data_storage_system.common.dto.AddressDto;
import org.example.dev_310_1_network_client_data_storage_system.common.dto.ClientDto;
import org.example.dev_310_1_network_client_data_storage_system.dto.ClientAddressDto;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ClientAddressService {
    Stream<ClientAddressDto> findAll();

    void createClient(ClientAddressDto client);

    void updateClient(ClientAddressDto clientAddressDto);

    Optional<ClientDto> findByIdClient(Long id);
    void createAddress(ClientAddressDto client) throws NoSuchObjectException;
     Optional<AddressDto> findByMacAddress(String mac);
    List<ClientAddressDto> findAllClientWithNameOrAddress(String data, String nameOrAddress);
    List<ClientAddressDto> filterClientName(List<ClientAddressDto> clients, String data);
    List<ClientAddressDto> filterClientAddress(List<ClientAddressDto> clients, String data);
    List<ClientAddressDto> filterClientType(String type, List<ClientAddressDto> list);



}
