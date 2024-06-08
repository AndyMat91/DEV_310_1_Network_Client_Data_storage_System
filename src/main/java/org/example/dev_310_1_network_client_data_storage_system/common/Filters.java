package org.example.dev_310_1_network_client_data_storage_system.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Filters {

    private String filterType;
    private String filterNameOrAddress;


}
