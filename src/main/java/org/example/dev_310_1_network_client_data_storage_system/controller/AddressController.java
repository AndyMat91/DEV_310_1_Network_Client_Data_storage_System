package org.example.dev_310_1_network_client_data_storage_system.controller;

import jakarta.validation.Valid;
import org.example.dev_310_1_network_client_data_storage_system.common.dto.AddressDto;
import org.example.dev_310_1_network_client_data_storage_system.dto.ClientAddressDto;
import org.example.dev_310_1_network_client_data_storage_system.service.AddressService;
import org.example.dev_310_1_network_client_data_storage_system.service.ClientAddressService;
import org.example.dev_310_1_network_client_data_storage_system.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;
    private final ClientService clientService;
    private final ClientAddressService clientAddressService;
    private Long id;

    public AddressController(AddressService addressService, ClientService clientService, ClientAddressService clientAddressService) {
        this.addressService = addressService;
        this.clientService = clientService;
        this.clientAddressService = clientAddressService;
    }

    @GetMapping("/{id}")
    public String addNewAddress(@PathVariable("id") Long id, Model model) {
        this.id = id;
        AddressDto address = new AddressDto();
        model.addAttribute("address", address);
        return "create_address_page";
    }

    @PostMapping("/create-address")
    public String createAddress(@Valid @ModelAttribute("address") AddressDto address, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            address.setClientDto(clientService.findByKey(id).get());
            addressService.create(address);
            return "redirect:/client-address";
        }
        return "create_address_page";
    }

    @GetMapping("/remove-address/{mac}/{id}")
    public String removeByMacFromClients(@PathVariable("id") Long id, @PathVariable("mac") String mac) {
        addressService.remove(mac);
        List<AddressDto> addressDto = addressService.findAddressByClientId(id).toList();
        if (addressDto.isEmpty()) {
            clientService.remove(id);
        }
        return "redirect:/client-address";
    }

    @PostMapping("/save-update-address")
    public String saveAddress(@Valid @ModelAttribute("client") ClientAddressDto client, BindingResult bindingResult) throws NoSuchObjectException {
        if (bindingResult.hasErrors()) {
            return "address_update_page";
        } else {
            clientAddressService.createAddress(client);
            return "redirect:/client-address";
        }
    }

    @GetMapping("/update-address/{id}/{mac}")
    public String updateAddress(@PathVariable("id") Long id, @PathVariable("mac") String mac, Model model) {
        ClientAddressDto clientAddressDto = ClientAddressDto.builder().build();

        clientAddressService.findByIdClient(id).ifPresent(clientDto -> {
            clientAddressDto.setClientId(clientDto.getClientId());
            clientAddressDto.setClientType((clientDto.getClientType().getValue()));
            clientAddressDto.setClientName(clientDto.getClientName());
            clientAddressDto.setDatereg(clientDto.getDatereg());
        });

        clientAddressService.findByMacAddress(mac).ifPresent(addressDto -> {
            clientAddressDto.setIp(addressDto.getIp());
            clientAddressDto.setMac(addressDto.getMac());
            clientAddressDto.setModel(addressDto.getModel());
            clientAddressDto.setAddress(addressDto.getAddress());
        });

        addressService.remove(mac);
        model.addAttribute("client", clientAddressDto);
        return "address_update_page";
    }
}
