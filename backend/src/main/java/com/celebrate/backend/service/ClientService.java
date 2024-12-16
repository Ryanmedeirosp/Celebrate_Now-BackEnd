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
import com.celebrate.backend.models.dto.CreateClient;
import com.celebrate.backend.models.dto.GetClients;
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

    public List<GetClients> getAllClients(Integer idCeremonialist) {
        List<Client> clients = clientRepository.findAllByCeremonialistId(idCeremonialist);
        List<GetClients> response = new ArrayList<>();
        for (Client client : clients) {
            response.add(new GetClients(client.getName(), client.getEmail(), client.getPhone()));
        }
        return response;
    }

    public void createClient(CreateClient request){

        Ceremonialist ceremonialist = ceremonialistRepository.findByEmail(request.getCeremonialistEmail())
        .orElseThrow(() -> new RuntimeException("Ceremonialista não encontrado"));

        List<Budget> budget = new ArrayList<>();

        Address address = getAddressByCep(request);

        Client client = addDataToClient(request);

        client.setCeremonialist(ceremonialist);
        client.setBudget(budget);
        client.setAddress(address);

        clientRepository.save(client);
    }

    public void updateClientById(Integer clientId, CreateClient request){

        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        updateClientData(client, request);
        
        clientRepository.save(client);
    }

    private Client addDataToClient(CreateClient request){

        Client client = new Client();

        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setPassword(request.getPassword());
        client.setCpf(request.getCpf());
        client.setBirthday(request.getBirthday());
        client.setPhone(request.getPhone());

        return client;
    }

    private void updateClientData(Client client, CreateClient request){

        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setPassword(request.getPassword());
        client.setCpf(request.getCpf());
        client.setBirthday(request.getBirthday());
        client.setPhone(request.getPhone());

        Address address = client.getAddress();

        updateAddress(address, request);
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

    private void updateAddress(Address address, CreateClient request){

        CepResponse cepResponse = viaCepClient.getAddressByCep(request.getCep());

        address.setCep(request.getCep());
        address.setState(cepResponse.getEstado());
        address.setCity(cepResponse.getLocalidade());
        address.setDistrict(cepResponse.getBairro());
        address.setStreet(cepResponse.getLogradouro());
        address.setHouseNumber(request.getHouseNumber());

        addressRepository.save(address);
    }
}
