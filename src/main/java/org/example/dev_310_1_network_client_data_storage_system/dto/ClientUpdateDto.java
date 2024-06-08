package org.example.dev_310_1_network_client_data_storage_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ClientUpdateDto {
    private Long clientId;

    @NotBlank(message = "Имя клиента не может быть пустым!")
    @Size(max = 100, message = "Имя клиента не может содержать более 100 символов!")
    @Pattern(regexp = "^$|[а-яА-Я-,. ]+", message = "Недопустимые символы в имени клиента!")
    private String clientName;

    @NotBlank(message = "Тип клиента не может быть пустым!")
    @Size(max = 20, message = "Тип клиента не может содержать более 20 символов!")
    private String clientType;

    private Instant datereg;


    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + clientId +
                ", name='" + clientName + '\'' +
                ", type=" + clientType +
                ", added=" + datereg +
                '}';
    }
}
