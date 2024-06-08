package org.example.dev_310_1_network_client_data_storage_system.service.impl;

import org.example.dev_310_1_network_client_data_storage_system.common.dto.AddressDto;
import org.example.dev_310_1_network_client_data_storage_system.common.entity.AddressEntity;
import org.example.dev_310_1_network_client_data_storage_system.common.exceptions.NoObjectException;
import org.example.dev_310_1_network_client_data_storage_system.repository.AddressRepository;
import org.example.dev_310_1_network_client_data_storage_system.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Optional<AddressDto> create(AddressDto addressDto) {
        return addressRepository.create(dtoToEntity(addressDto))
                .map(AddressServiceImpl::entityToDto);
    }

    @Override
    public void remove(String mac) throws NoObjectException, UnknownError{
        try {
        addressRepository.remove(mac);
        } catch (Exception e) {
            if (e instanceof NoObjectException) {
                throw new NoObjectException(String.format("Адрес с mac: '%d' не найден", mac));
            } else throw new UnknownError("Неизвестная ошибка, обратитесь к администратору");
        }
    }

    @Override
    public void update(AddressDto addressDto) throws NoObjectException, UnknownError{
        try {
            addressRepository.update(dtoToEntity(addressDto));
        } catch (Exception e) {
            if (e instanceof NoObjectException) {
                throw new NoObjectException(String.format("Не удалось обновить AddressDto"));
            } else throw new UnknownError("Неизвестная ошибка, обратитесь к администратору");
        }
    }

    @Override
    public Stream<AddressDto> findAll() {
        return addressRepository.findAll().map(AddressServiceImpl::entityToDto);
    }

    @Override
    public Optional<AddressDto> findByKey(String mac) {
        return addressRepository.findAddressByMac(mac).map(AddressServiceImpl::entityToDto);
    }

    @Override
    public Stream<AddressDto> findAddressByClientId(Long id) {
        return addressRepository.findAddressByClientId(id).map(AddressServiceImpl::entityToDto);
    }

    public static AddressDto entityToDto(AddressEntity entity){
        return AddressDto.builder()
                .ip(entity.getIp())
                .address(entity.getAddress())
                .mac(entity.getMac())
                .model(entity.getModel())
                .address(entity.getAddress())
                .clientDto(ClientServiceImpl.entityToDto(entity.getClient()))
                .build();
    }

    public static AddressEntity dtoToEntity(AddressDto dto){
        AddressEntity entity = new AddressEntity();
        entity.setIp(dto.getIp());
        entity.setAddress(dto.getAddress());
        entity.setMac(dto.getMac());
        entity.setModel(dto.getModel());
        entity.setAddress(dto.getAddress());
        entity.setClient(ClientServiceImpl.dtoToEntity(dto.getClientDto()));
        return entity;
    }
}
