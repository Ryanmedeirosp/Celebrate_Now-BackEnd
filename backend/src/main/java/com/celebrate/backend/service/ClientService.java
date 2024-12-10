package com.celebrate.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.celebrate.backend.client.ViaCepClient;
import com.celebrate.backend.client.response.CepResponse;
import com.celebrate.backend.models.Address;
import com.celebrate.backend.models.Budget;
import com.celebrate.backend.models.Ceremonialist;
import com.celebrate.backend.models.Client;
import com.celebrate.backend.models.Dto.CreateClient;
import com.celebrate.backend.repository.AddressRepository;
import com.celebrate.backend.repository.CeremonialistRepository;
import com.celebrate.backend.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final CeremonialistRepository ceremonialistRepository;

    private final AddressRepository addressRepository;
    private final ViaCepClient viaCepClient;

    public ClientService(ClientRepository clientRepository, CeremonialistRepository ceremonialistRepository, AddressRepository addressRepository, ViaCepClient viaCepClient){

        this.clientRepository = clientRepository;
        this.ceremonialistRepository = ceremonialistRepository;
        this.addressRepository = addressRepository;
        this.viaCepClient = viaCepClient;
    }

    public void createClient(CreateClient request){

        List<Budget> budget = new ArrayList<>();

        Address address = getAddressByCep(request);

        Client client = addDataToClient(request);

        Ceremonialist ceremonialist = ceremonialistRepository.findByEmail(request.getCeremonialistEmail())
        .orElseThrow(() -> new RuntimeException("Ceremonialista não encontrado"));

        client.setCeremonialist(ceremonialist);
        client.setBudget(budget);
        client.setAddress(address);

        clientRepository.save(client);
    }

    public Client addDataToClient(CreateClient request){

        Client client = new Client();

        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setPassword(request.getPassword());
        client.setCpf(request.getCpf());
        client.setBirthday(request.getBirthday());
        client.setPhone(request.getPhone());

        return client;
    }

    private Address getAddressByCep(CreateClient request){

        CepResponse cepResponse = viaCepClient.getAddressByCep(request.getCep());
        
        Address address = new Address();

        address.setCep(request.getCep());
        address.setState(cepResponse.getEstado());
        address.setCity(cepResponse.getLocalidade());
        address.setDistrict(cepResponse.getBairro());
        address.setStreet(cepResponse.getLogradouro());
        address.setHouseNumber(request.getHouseNumber());

        addressRepository.save(address);

        return address;
    }


}
