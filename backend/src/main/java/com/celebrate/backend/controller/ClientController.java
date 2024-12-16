package com.celebrate.backend.controller;

import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celebrate.backend.models.dto.CreateClient;
import com.celebrate.backend.models.dto.GetClients;
import com.celebrate.backend.service.ClientService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {
    
    private final ClientService clientService;

    @PostMapping()
    public void createClient(@RequestBody CreateClient request){

        clientService.createClient(request);
    }
    
    @GetMapping("{idCeremonialist}")
    public List<GetClients> getAllClients(@PathVariable Integer idCeremonialist){
        return clientService.getAllClients(idCeremonialist);
    }

    @PutMapping("/{clientId}")
    public void updateClientByEmail(@PathVariable Integer clientId, @RequestBody CreateClient request){

        clientService.updateClientById(clientId, request);
    }
}
