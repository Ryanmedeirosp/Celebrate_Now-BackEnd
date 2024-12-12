package com.celebrate.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celebrate.backend.models.Client;
import com.celebrate.backend.models.Dto.CreateClient;
import com.celebrate.backend.models.Dto.GetClients;
import com.celebrate.backend.service.ClientService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {
    
    private final ClientService clientService;

    @PostMapping()
    public void createClient(@RequestBody CreateClient request){

        System.out.println(request);
        clientService.createClient(request);
    }

    @GetMapping("{idCeremonialist}")
    public List<GetClients> getAllClients(@PathVariable Integer idCeremonialist){
        return clientService.getAllClients(idCeremonialist);
    }
}
