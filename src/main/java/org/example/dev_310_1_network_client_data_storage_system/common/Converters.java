package org.example.dev_310_1_network_client_data_storage_system.common;

import org.example.dev_310_1_network_client_data_storage_system.common.dto.AddressDto;
import org.example.dev_310_1_network_client_data_storage_system.common.dto.ClientDto;
import org.example.dev_310_1_network_client_data_storage_system.common.enums.CLIENT_TYPE;
import org.example.dev_310_1_network_client_data_storage_system.dto.ClientAddressDto;
import org.example.dev_310_1_network_client_data_storage_system.dto.ClientUpdateDto;

public class Converters {
    public static ClientAddressDto converterClientUpdateDtoToClientDto(ClientDto clientDto, AddressDto addressDto){
        return ClientAddressDto.builder()

                .clientId(clientDto.getClientId())
                .clientName(clientDto.getClientName())
                .clientType(clientDto.getClientType().getValue())
                .datereg(clientDto.getDatereg())
                .ip(addressDto!=null ? addressDto.getIp() : null)
                .mac(addressDto!=null ? addressDto.getMac() : null)
                .model(addressDto!=null ? addressDto.getModel() : null)
                .address(addressDto!=null ? addressDto.getAddress() : null)
                .build();
    }

    public static ClientAddressDto converterClientUpdateDtoToClientDto(AddressDto addressDto) {
        return ClientAddressDto.builder()
                .clientId(addressDto.getClientDto().getClientId())
                .clientName(addressDto.getClientDto().getClientName())
                .clientType(addressDto.getClientDto().getClientType().getValue())
                .datereg(addressDto.getClientDto().getDatereg())
                .ip(addressDto.getIp())
                .mac(addressDto.getMac())
                .model(addressDto.getModel())
                .address(addressDto.getAddress())
                .build();
    }

    public static ClientAddressDto converterClientUpdateDtoToClientDto(ClientDto clientDto) {
        return ClientAddressDto.builder()
                .clientId(clientDto.getClientId())
                .clientName(clientDto.getClientName())
                .clientType(clientDto.getClientType().getValue())
                .datereg(clientDto.getDatereg())
                .build();
    }

    public static ClientDto converterClientUpdateDtoToClientDto(ClientAddressDto client){
        return ClientDto.builder()
                .clientId(client.getClientId())
                .clientName(client.getClientName())
                .datereg(client.getDatereg())
                .clientType(CLIENT_TYPE.getType(client.getClientType()))
                .build();
    }

    public static ClientUpdateDto convertClientDtoToClientUpdateDto(ClientDto client){
        return ClientUpdateDto.builder()
                .clientId(client.getClientId())
                .clientName(client.getClientName())
                .datereg(client.getDatereg())
                .clientType(client.getClientType().getValue())
                .build();
    }

    public static ClientDto converterClientUpdateDtoToClientDto(ClientUpdateDto client){
        return ClientDto.builder()
                .clientId(client.getClientId())
                .clientName(client.getClientName())
                .datereg(client.getDatereg())
                .clientType(CLIENT_TYPE.getType(client.getClientType()))
                .build();
    }

    public static ClientAddressDto convertClientUpdateDtoToClientAddressDto(ClientUpdateDto client){
        return ClientAddressDto.builder()
                .clientId(client.getClientId())
                .clientName(client.getClientName())
                .clientType(client.getClientType())
                .datereg(client.getDatereg())
                .build();
    }

    public static AddressDto converterClientAddressDtoToAddressDto (ClientAddressDto client) {
        return AddressDto.builder()
                .ip(client.getIp())
                .mac(client.getMac())
                .model(client.getModel())
                .clientDto(converterClientUpdateDtoToClientDto(client))
                .build();
    }
}
