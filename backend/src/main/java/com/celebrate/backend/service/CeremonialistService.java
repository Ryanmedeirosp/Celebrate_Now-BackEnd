package com.celebrate.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.celebrate.backend.client.ViaCepClient;
import com.celebrate.backend.client.response.CepResponse;
import com.celebrate.backend.models.Address;
import com.celebrate.backend.models.Ceremonialist;
import com.celebrate.backend.models.Client;
import com.celebrate.backend.models.dto.CreateCeremonialist;
import com.celebrate.backend.repository.AddressRepository;
import com.celebrate.backend.repository.CeremonialistRepository;

@Service
public class CeremonialistService {

    private final CeremonialistRepository ceremonialistRepository;
    private final AddressRepository addressRepository;
    private final ViaCepClient viaCepClient;

    public CeremonialistService(CeremonialistRepository ceremonialistRepository, AddressRepository addressRepository,ViaCepClient viaCepClient) {
        this.ceremonialistRepository = ceremonialistRepository;
        this.addressRepository = addressRepository;
        this.viaCepClient = viaCepClient;
    }


    public void updateCeremonialisByEmail(String ceremonialistEmail, CreateCeremonialist request){

        Ceremonialist ceremonialist = ceremonialistRepository.findByEmail(ceremonialistEmail)
            .orElseThrow(() -> new RuntimeException());

        Address address = getAddressByCep(request);

        ceremonialist.setAddress(address);

        updateCeremonialistData(ceremonialist, request);

        ceremonialistRepository.save(ceremonialist);
    }

    public void createCeremonialist(CreateCeremonialist request){

        //Criação do Endereço
        Address address = getAddressByCep(request);

        //Criação da lista de Clientes
        List<Client> clients = new ArrayList<>();

        //Adição dos dados do Cerimonialista
        Ceremonialist ceremonialist = addDataToCeremonialist(request);

        ceremonialist.setAddress(address);
        ceremonialist.setClients(clients);

        ceremonialistRepository.save(ceremonialist);
    }

    private Ceremonialist addDataToCeremonialist(CreateCeremonialist request){



        Ceremonialist ceremonialist = new Ceremonialist();
        
        if(ceremonialistRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email já cadastrado");
        };

        ceremonialist.setName(request.getName());
        ceremonialist.setEmail(request.getEmail());
        ceremonialist.setPassword(request.getPassword());
        ceremonialist.setDocument(request.getDocument());
        ceremonialist.setBirthday(request.getBirthday());
        ceremonialist.setPhone(request.getPhone());

        return ceremonialist;
    }

    private void updateCeremonialistData(Ceremonialist ceremonialist, CreateCeremonialist request){

        ceremonialist.setName(request.getName());
        ceremonialist.setEmail(request.getEmail());
        ceremonialist.setPassword(request.getPassword());
        ceremonialist.setDocument(request.getDocument());
        ceremonialist.setBirthday(request.getBirthday());
        ceremonialist.setPhone(request.getPhone());
    }

    private Address getAddressByCep(CreateCeremonialist request){

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
