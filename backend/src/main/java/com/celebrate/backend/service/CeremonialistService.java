package com.celebrate.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.celebrate.backend.client.ViaCepClient;
import com.celebrate.backend.client.response.CepResponse;
import com.celebrate.backend.exception.EmailAlreadyExistsException;
import com.celebrate.backend.exception.InvalidDataException;
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

    public CeremonialistService(CeremonialistRepository ceremonialistRepository, 
                                AddressRepository addressRepository, 
                                ViaCepClient viaCepClient) {
        this.ceremonialistRepository = ceremonialistRepository;
        this.addressRepository = addressRepository;
        this.viaCepClient = viaCepClient;
    }

    public void createCeremonialist(CreateCeremonialist request) {
        validateCeremonialistData(request);

        // Criação do Endereço
        Address address = getAddressByCep(request);

        // Criação da lista de Clientes
        List<Client> clients = new ArrayList<>();

        // Adição dos dados do Cerimonialista
        Ceremonialist ceremonialist = addDataToCeremonialist(request);

        ceremonialist.setAddress(address);
        ceremonialist.setClients(clients);

        ceremonialistRepository.save(ceremonialist);
    }

    public void updateCeremonialistById(Integer ceremonialistId, CreateCeremonialist request) {
        validateCeremonialistData(request);

        Ceremonialist ceremonialist = ceremonialistRepository.findById(ceremonialistId)
            .orElseThrow(() -> new InvalidDataException("Cerimonialista não encontrado"));

        updateCeremonialistData(ceremonialist, request);

        ceremonialistRepository.save(ceremonialist);
    }

    // Funções de Auxílio

    private Ceremonialist addDataToCeremonialist(CreateCeremonialist request) {
        if (ceremonialistRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email já cadastrado.");
        }
        
        if (ceremonialistRepository.findByDocument(request.getDocument()).isPresent()) {
            throw new InvalidDataException("CPF já cadastrado.");
        }

        Ceremonialist ceremonialist = new Ceremonialist();
        ceremonialist.setName(request.getName());
        ceremonialist.setEmail(request.getEmail());
        ceremonialist.setPassword(request.getPassword());
        ceremonialist.setDocument(request.getDocument());
        ceremonialist.setBirthday(request.getBirthday());
        ceremonialist.setPhone(request.getPhone());

        return ceremonialist;
    }

    private void updateCeremonialistData(Ceremonialist ceremonialist, CreateCeremonialist request) {
        ceremonialist.setName(request.getName());
        ceremonialist.setEmail(request.getEmail());
        ceremonialist.setPassword(request.getPassword());
        ceremonialist.setDocument(request.getDocument());
        ceremonialist.setBirthday(request.getBirthday());
        ceremonialist.setPhone(request.getPhone());

        Address address = ceremonialist.getAddress();
        updateAddress(address, request);
    }

    private Address getAddressByCep(CreateCeremonialist request) {
        CepResponse cepResponse = viaCepClient.getAddressByCep(request.getCep());
        if (cepResponse == null) {
            throw new InvalidDataException("CEP inválido ou não encontrado.");
        }

        Address address = new Address();
        address.setCep(request.getCep());
        address.setState(cepResponse.getEstado());
        address.setCity(cepResponse.getLocalidade());
        address.setDistrict(cepResponse.getBairro());
        address.setStreet(cepResponse.getLogradouro());
        address.setHouseNumber(request.getHouseNumber());

        return addressRepository.save(address);
    }

    private void updateAddress(Address address, CreateCeremonialist request) {
        CepResponse cepResponse = viaCepClient.getAddressByCep(request.getCep());
        if (cepResponse == null) {
            throw new InvalidDataException("CEP inválido ou não encontrado.");
        }

        address.setCep(request.getCep());
        address.setState(cepResponse.getEstado());
        address.setCity(cepResponse.getLocalidade());
        address.setDistrict(cepResponse.getBairro());
        address.setStreet(cepResponse.getLogradouro());
        address.setHouseNumber(request.getHouseNumber());

        addressRepository.save(address);
    }

    private void validateCeremonialistData(CreateCeremonialist request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new InvalidDataException("O nome do cerimonialista é obrigatório.");
        }
        if (request.getEmail() == null || !request.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidDataException("Email inválido.");
        }
        if (request.getPassword() == null || request.getPassword().length() < 8) {
            throw new InvalidDataException("A senha deve ter pelo menos 8 caracteres.");
        }
        if (request.getDocument() == null || request.getDocument().length() != 11) {
            throw new InvalidDataException("Documento inválido. Deve conter 11 caracteres.");
        }
        if (request.getPhone() == null || !request.getPhone().matches("^\\+?[1-9][0-9]{1,14}$")) {
            throw new InvalidDataException("Número de telefone inválido.");
        }
        if (request.getCep() == null || !request.getCep().matches("\\d{8}")) {
            throw new InvalidDataException("CEP inválido.");
        }
    }
}
