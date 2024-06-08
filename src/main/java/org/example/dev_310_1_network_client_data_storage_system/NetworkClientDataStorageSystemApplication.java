package org.example.dev_310_1_network_client_data_storage_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class NetworkClientDataStorageSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetworkClientDataStorageSystemApplication.class, args);
	}

}
