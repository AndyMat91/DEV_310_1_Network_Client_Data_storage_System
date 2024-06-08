package org.example.dev_310_1_network_client_data_storage_system.service.impl;

import org.example.dev_310_1_network_client_data_storage_system.common.dto.ClientDto;
import org.example.dev_310_1_network_client_data_storage_system.common.entity.ClientEntity;
import org.example.dev_310_1_network_client_data_storage_system.common.enums.CLIENT_TYPE;
import org.example.dev_310_1_network_client_data_storage_system.common.exceptions.NoObjectException;
import org.example.dev_310_1_network_client_data_storage_system.repository.ClientRepository;
import org.example.dev_310_1_network_client_data_storage_system.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Optional<ClientDto> create(ClientDto clientDto) {
        return clientRepository.create(dtoToEntity(clientDto)).map(ClientServiceImpl::entityToDto);
    }

    @Override
    public void remove(Long id) throws NoObjectException, UnknownError {
        try {
            clientRepository.remove(id);
        } catch (Exception e) {
            if (e instanceof NoObjectException) {
                throw new NoObjectException(String.format("Объект с идентификатором '%d' не найден", id));
            } else throw new UnknownError("Неизвестная ошибка, обратитесь к администратору");
        }
    }

    @Override
    public void update(ClientDto clientDto) throws NoObjectException, UnknownError {
        try {
            clientRepository.update(dtoToEntity(clientDto));
        } catch (Exception e) {
            if (e instanceof NoObjectException) {
                throw new NoObjectException(String.format("Не удалось обновить ClientDto"));
            } else throw new UnknownError("Неизвестная ошибка, обратитесь к администратору");
        }
    }

    @Override
    public Stream<ClientDto> findAll() {
        return clientRepository.findAll().map(ClientServiceImpl::entityToDto);
    }

    @Override
    public Optional<ClientDto> findByKey(Long id) {
        return clientRepository.findClientById(id).map(ClientServiceImpl::entityToDto);
    }


    public static ClientDto entityToDto(ClientEntity entity){
        return ClientDto.builder()
                .clientId(entity.getClientId())
                .clientName(entity.getClientName())
                .clientType(CLIENT_TYPE.getType(entity.getClientType()))
                .datereg(entity.getDatereg())
                .build();
    }

    public static ClientEntity dtoToEntity(ClientDto dto){
        ClientEntity entity = new ClientEntity();
        entity.setClientId(dto.getClientId());
        entity.setClientName(dto.getClientName());
        entity.setClientType(dto.getClientType().getValue());
        entity.setDatereg(dto.getDatereg());
        return entity;
    }
}
