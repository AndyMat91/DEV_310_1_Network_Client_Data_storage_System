package org.example.dev_310_1_network_client_data_storage_system.controller;

import jakarta.validation.Valid;
import org.example.dev_310_1_network_client_data_storage_system.dto.ClientAddressDto;
import org.example.dev_310_1_network_client_data_storage_system.dto.ClientUpdateDto;
import org.example.dev_310_1_network_client_data_storage_system.service.ClientAddressService;
import org.example.dev_310_1_network_client_data_storage_system.service.ClientService;
import org.example.dev_310_1_network_client_data_storage_system.common.Converters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.time.Instant;


@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final ClientAddressService clientAddressService;
    private Instant datereg;

    public ClientController(ClientService clientService, ClientAddressService clientAddressService) {
        this.clientService = clientService;
        this.clientAddressService = clientAddressService;
    }

    @GetMapping
    public String returnClientPage() {
        return "create_client_page";
    }
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        clientService.findByKey(id).ifPresent(e -> model.addAttribute("client", e));
        model.addAttribute("clientaddress", new ClientAddressDto());
        return "client_page";
    }

    @GetMapping("/remove/{id}")
    public String removeById(@PathVariable("id") Long id, Model model) {
        clientService.remove(id);
        return "redirect:/client";
    }


    @GetMapping("/add-new-client")
    public String addNewClient(Model model){

        ClientAddressDto client = new ClientAddressDto();
        model.addAttribute("client", client);

        return "create_client_page";
    }

    @PostMapping("/create-client")
    public String createClient(@Valid @ModelAttribute("client") ClientAddressDto client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create_client_page";
        } else {
            clientAddressService.createClient(client);
            return "redirect:/client-address";
        }
    }

    @GetMapping("/update-client/{id}")
    public String updateClient(@PathVariable("id") Long id, Model model) throws NoSuchObjectException {
        clientService.findByKey(id).ifPresent(clientDto -> {
            datereg = clientDto.getDatereg();
            ClientUpdateDto clientUpdateDto = Converters.convertClientDtoToClientUpdateDto(clientDto);
            model.addAttribute("client", clientUpdateDto);
        });

        return "client_update_page";
    }

    @PostMapping("/save-update-client")
    public String saveUpdateClient(@Valid @ModelAttribute("client") ClientUpdateDto client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "client_update_page";
        } else {
            client.setDatereg(datereg);
            clientService.update(Converters.converterClientUpdateDtoToClientDto(client));
            return "redirect:/client-address";
        }
    }
}

