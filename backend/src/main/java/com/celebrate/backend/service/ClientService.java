package com.celebrate.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.celebrate.backend.client.ViaCepClient;
import com.celebrate.backend.client.response.CepResponse;
import com.celebrate.backend.exception.InvalidClientDataException;
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

    public ClientService(ClientRepository clientRepository, 
                         CeremonialistRepository ceremonialistRepository,
                         AddressRepository addressRepository, 
                         ViaCepClient viaCepClient) {
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

    public void createClient(CreateClient request) {
        Ceremonialist ceremonialist = ceremonialistRepository.findByEmail(request.getCeremonialistEmail())
            .orElseThrow(() -> new InvalidClientDataException("Ceremonialista não encontrado"));

        validateClientData(request);

        Address address = getAddressByCep(request);

        Client client = new Client();
        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setPassword(request.getPassword());
        client.setCpf(request.getCpf());
        client.setBirthday(request.getBirthday());
        client.setPhone(request.getPhone());
        client.setCeremonialist(ceremonialist);
        client.setBudget(new ArrayList<>());
        client.setAddress(address);

        clientRepository.save(client);
    }

    public void updateClientById(Integer clientId, CreateClient request) {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new InvalidClientDataException("Cliente não encontrado"));

        validateClientData(request);

        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setPassword(request.getPassword());
        client.setCpf(request.getCpf());
        client.setBirthday(request.getBirthday());
        client.setPhone(request.getPhone());

        updateAddress(client.getAddress(), request);

        clientRepository.save(client);
    }

    private void validateClientData(CreateClient request) {
        if (!isValidEmail(request.getEmail())) {
            throw new InvalidClientDataException("Email inválido.");
        }
        if (!isValidCPF(request.getCpf())) {
            throw new InvalidClientDataException("CPF inválido.");
        }
        if (!isValidPhoneNumber(request.getPhone())) {
            throw new InvalidClientDataException("Número de telefone inválido.");
        }
    }

    private Address getAddressByCep(CreateClient request) {
        CepResponse cepResponse = viaCepClient.getAddressByCep(request.getCep());
        if (cepResponse == null) {
            throw new InvalidClientDataException("CEP inválido ou não encontrado.");
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

    private void updateAddress(Address address, CreateClient request) {
        CepResponse cepResponse = viaCepClient.getAddressByCep(request.getCep());
        if (cepResponse == null) {
            throw new InvalidClientDataException("CEP inválido ou não encontrado.");
        }

        address.setCep(request.getCep());
        address.setState(cepResponse.getEstado());
        address.setCity(cepResponse.getLocalidade());
        address.setDistrict(cepResponse.getBairro());
        address.setStreet(cepResponse.getLogradouro());
        address.setHouseNumber(request.getHouseNumber());

        addressRepository.save(address);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }

    private boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.chars().distinct().count() == 1) {
            return false;
        }
        try {
            int sum = 0, weight = 10;
            for (int i = 0; i < 9; i++) {
                sum += (cpf.charAt(i) - '0') * weight--;
            }
            int firstCheckDigit = (sum * 10) % 11;
            if (firstCheckDigit == 10) firstCheckDigit = 0;
            if (firstCheckDigit != (cpf.charAt(9) - '0')) return false;

            sum = 0;
            weight = 11;
            for (int i = 0; i < 10; i++) {
                sum += (cpf.charAt(i) - '0') * weight--;
            }
            int secondCheckDigit = (sum * 10) % 11;
            if (secondCheckDigit == 10) secondCheckDigit = 0;

            return secondCheckDigit == (cpf.charAt(10) - '0');
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidPhoneNumber(String phone) {
        String phoneRegex = "^\\+?[1-9][0-9]{1,14}$";
        return Pattern.matches(phoneRegex, phone);
    }
}
