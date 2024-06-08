package org.example.dev_310_1_network_client_data_storage_system.service;

import org.example.dev_310_1_network_client_data_storage_system.common.dto.AddressDto;
import org.example.dev_310_1_network_client_data_storage_system.service.common.ServiceStrategy;

import java.util.stream.Stream;

public interface AddressService extends ServiceStrategy<AddressDto, String> {
    Stream<AddressDto> findAddressByClientId(Long id);
}
