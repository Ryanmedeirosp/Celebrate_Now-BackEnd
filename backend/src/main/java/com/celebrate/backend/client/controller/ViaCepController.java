package com.celebrate.backend.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celebrate.backend.client.response.CepResponse;
import com.celebrate.backend.client.service.ViaCepService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/viacep")
public class ViaCepController {
    
    private final ViaCepService viaCepService;

    @GetMapping("/{cep}")
    public CepResponse getAddressByCep(@PathVariable String cep){
        
        return viaCepService.getAddressByCep(cep);
    }

}
