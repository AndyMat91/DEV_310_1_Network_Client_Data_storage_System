package org.example.dev_310_1_network_client_data_storage_system.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.example.dev_310_1_network_client_data_storage_system.common.enums.CLIENT_TYPE;

import java.time.Instant;

@Data
@Builder
public class ClientDto {
    private Long clientId;

    @NotBlank(message = "Имя клиента не может быть пустым!")
    @Size(max = 100, message = "Имя клиента не может содержать более 100 символов!")
    @Pattern(regexp = "^$|[а-яА-Я-,. ]+", message = "Недопустимые символы в имени клиента!")
    private String clientName;

    private CLIENT_TYPE clientType;

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
