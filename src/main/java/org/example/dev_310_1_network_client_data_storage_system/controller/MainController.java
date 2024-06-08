package org.example.dev_310_1_network_client_data_storage_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainMethod(){

        return "redirect:/client-address";
    }
}
