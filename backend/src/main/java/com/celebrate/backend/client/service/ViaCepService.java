package com.celebrate.backend.client.service;

import org.springframework.stereotype.Service;

import com.celebrate.backend.client.ViaCepClient;
import com.celebrate.backend.client.response.CepResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ViaCepService {
    
    private final ViaCepClient viaCepClient;

    public CepResponse getAddressByCep(String cep){
        return viaCepClient.getAddressByCep(cep);
    }
}
