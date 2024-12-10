package com.celebrate.backend.service;

import org.springframework.stereotype.Service;

import com.celebrate.backend.client.ViaCepClient;
import com.celebrate.backend.repository.AddressRepository;
import com.celebrate.backend.repository.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final ViaCepClient viaCepClient;

    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository, ViaCepClient viaCepClient){

        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.viaCepClient = viaCepClient;
    }
}
