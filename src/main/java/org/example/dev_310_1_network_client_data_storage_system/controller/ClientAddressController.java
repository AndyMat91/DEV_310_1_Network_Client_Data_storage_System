package org.example.dev_310_1_network_client_data_storage_system.controller;

import org.example.dev_310_1_network_client_data_storage_system.common.Converters;
import org.example.dev_310_1_network_client_data_storage_system.common.Filters;
import org.example.dev_310_1_network_client_data_storage_system.dto.ClientAddressDto;
import org.example.dev_310_1_network_client_data_storage_system.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client-address")
public class ClientAddressController {
    private final ClientAddressService clientAddressService;
    private final ClientService clientService;
    private final AddressService addressService;


    public ClientAddressController(ClientAddressService clientAddressService, ClientService clientService, AddressService addressService) {
        this.clientAddressService = clientAddressService;
        this.clientService = clientService;
        this.addressService = addressService;
    }


    @GetMapping
    public String findAll(@ModelAttribute("filters") Filters filters, Model model) {
        List<ClientAddressDto> clients;
        String type = filters.getFilterType();
        String nameOrAddress = filters.getFilterNameOrAddress();
        clients = clientAddressService.findAllClientWithNameOrAddress(type,nameOrAddress);
        model.addAttribute("clients", clients);
        return "clients_page";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        List<ClientAddressDto> clients = addressService.findAddressByClientId(id).map(Converters::converterClientUpdateDtoToClientDto).toList();
        model.addAttribute("clients", clients);
        return "client_page";
    }

    @GetMapping("/remove/{id}")
    public String removeById(@PathVariable("id") Long id, Model model) {
        if (id != null) {
            clientService.remove(id);
        }
        return "redirect:/client-address";
    }
}
