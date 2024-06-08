package org.example.dev_310_1_network_client_data_storage_system.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    @NotBlank(message = "IP адрес клиента не может быть пустым!")
    @Size(max = 25, message = "IP адрес клиента не может содержать более 25 символов!")
    @Pattern(regexp = "^$|(([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])[.]){3}([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])", message = "Неверный формат IP адреса клиента!")
    private String ip;
    @NotBlank(message = "MAC адрес клиента не может быть пустым!")
    @Size(max = 20, message = "MAC адрес клиента не может содержать более 20 символов!")
    @Pattern(regexp = "^$|([0-9a-zA-Z]{2}[-]){5}[0-9a-zA-Z]{2}", message = "Неверный формат MAC адреса клиента!")
    private String mac;
    @NotBlank(message = "Модель клиента не может быть пустой!")
    @Size(max = 100, message = "Модель клиента не может содержать более 100 символов!")
    private String model;
    @NotBlank(message = "Адрес клиента не может быть пустым!")
    @Size(max = 200, message = "Адрес клиента не может содержать более 200 символов!")
    private String address;
    private ClientDto clientDto;

    public AddressDto(String ip, String mac, String model, String address, ClientDto clientDto) {
        this.ip = ip;
        this.mac = mac;
        this.model = model;
        this.address = address;
        this.clientDto = clientDto;
    }

    public AddressDto() {
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "ip='" + ip + '\'' +
                ", mac='" + mac + '\'' +
                ", model=" + model +
                ", address='" + address +
                '}';
    }
}
