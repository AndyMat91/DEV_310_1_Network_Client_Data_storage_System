package org.example.dev_310_1_network_client_data_storage_system.service.impl;

import org.example.dev_310_1_network_client_data_storage_system.common.dto.AddressDto;
import org.example.dev_310_1_network_client_data_storage_system.common.dto.ClientDto;
import org.example.dev_310_1_network_client_data_storage_system.common.entity.AddressEntity;
import org.example.dev_310_1_network_client_data_storage_system.common.entity.ClientEntity;
import org.example.dev_310_1_network_client_data_storage_system.dto.ClientAddressDto;
import org.example.dev_310_1_network_client_data_storage_system.repository.AddressRepository;
import org.example.dev_310_1_network_client_data_storage_system.repository.ClientRepository;
import org.example.dev_310_1_network_client_data_storage_system.service.AddressService;
import org.example.dev_310_1_network_client_data_storage_system.service.ClientAddressService;
import org.example.dev_310_1_network_client_data_storage_system.service.ClientService;
import org.example.dev_310_1_network_client_data_storage_system.common.Converters;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ClientAddressServiceImpl implements ClientAddressService {
    private final ClientService clientService;
    private final AddressService addressService;
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    public ClientAddressServiceImpl(
            @Qualifier(value = "clientServiceImpl") ClientService clientService,
            AddressService addressService, AddressRepository addressRepository, ClientRepository clientRepository
    ) {
        this.clientService = clientService;
        this.addressService = addressService;
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public void updateClient(ClientAddressDto clientAddressDto) {
        clientService.update(Converters.converterClientUpdateDtoToClientDto(clientAddressDto));
    }

    @Override
    public void createClient(ClientAddressDto client) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientName(client.getClientName());
        clientEntity.setClientType(client.getClientType());
        clientEntity.setDatereg(client.getDatereg());
        clientRepository.create(clientEntity);

        AddressEntity addressEntity = new AddressEntity();

        addressEntity.setIp(client.getIp());
        addressEntity.setMac(client.getMac());
        addressEntity.setModel(client.getModel());
        addressEntity.setAddress(client.getAddress());
        addressEntity.setClient(clientEntity);
        addressRepository.create(addressEntity);
    }

    @Override
    public void createAddress(ClientAddressDto client) throws NoSuchObjectException {

        clientRepository.findClientById(client.getClientId()).ifPresent(clientEntity -> {

            AddressEntity addressEntity = new AddressEntity();

            addressEntity.setIp(client.getIp());
            addressEntity.setMac(client.getMac());
            addressEntity.setModel(client.getModel());
            addressEntity.setAddress(client.getAddress());
            addressEntity.setClient(clientEntity);
            addressRepository.create(addressEntity);
        });

    }

    @Override
    public Stream<ClientAddressDto> findAll() {
        return clientService.findAll().flatMap(this::clientDtoToClientAddressDto);
    }

    public Stream<ClientAddressDto> clientDtoToClientAddressDto(ClientDto clientDto) {
        if (addressService.findAddressByClientId(clientDto.getClientId()).count() == 0) {
            return Stream.of(Converters.converterClientUpdateDtoToClientDto(clientDto, null));
        } else {
            return addressService.findAddressByClientId(clientDto.getClientId()).map(address -> Converters.converterClientUpdateDtoToClientDto(clientDto, address));
        }
    }

    @Override
    public Optional<ClientDto> findByIdClient(Long id) {
        return clientService.findByKey(id);
    }

    @Override
    public Optional<AddressDto> findByMacAddress(String mac) {
        return addressService.findByKey(mac);
    }

    @Override
    public List<ClientAddressDto> findAllClientWithNameOrAddress(String clientType, String clientNameOrAddress) {
        List<ClientAddressDto> clients = findAll().toList();
        List<ClientAddressDto> clientAddress = filterClientAddress(clients, clientNameOrAddress);
        List<ClientAddressDto> clientName = filterClientName(clients, clientNameOrAddress);
        if (!clientAddress.isEmpty() && clientAddress.size() != clients.size()) {
            clients = filterClientType(clientType, clientAddress);
        } else if (!clientName.isEmpty() && clientName.size() != clients.size()) {
            clients = filterClientType(clientType, clientName);
        } else {
            clients = filterClientType(clientType, clients);
        }
        return clients;
    }

    @Override
    public List<ClientAddressDto> filterClientName(List<ClientAddressDto> clients, String data) {
        if (data != null && !data.isEmpty()) {
            clients = clients.stream().filter(client -> client.getClientName().toLowerCase()
                    .contains(data.toLowerCase())).toList();
        }
        return clients;
    }

    @Override
    public List<ClientAddressDto> filterClientAddress(List<ClientAddressDto> clients, String data) {
        if (data != null && !data.isEmpty()) {
            clients = clients.stream().filter(client -> client.getAddress().toLowerCase()
                    .contains(data.toLowerCase())).toList();
        }
        return clients;
    }

    @Override
    public List<ClientAddressDto> filterClientType(String type, List<ClientAddressDto> list) {
        List<ClientAddressDto> newList = new ArrayList<>();
        if (type != null && !type.equals("") && !type.equals("--> Выберите тип <--")) {
            List<ClientAddressDto> finalNewList = newList;
            list.forEach(client -> {
                if (client.getClientType().equals(type)) {
                    finalNewList.add(client);
                }
            });
        } else newList = list;
        return newList;
    }
}
