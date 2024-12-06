package com.celebrate.backend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.celebrate.backend.client.response.CepResponse;

@FeignClient(name = "ViaCep", url = "https://viacep.com.br/ws")
// https://viacep.com.br/ws/  CEP  /json/

public interface ViaCepClient {

    @GetMapping(value = "/{cep}/json")
    CepResponse getAddressByCep(@PathVariable String cep);
} 
