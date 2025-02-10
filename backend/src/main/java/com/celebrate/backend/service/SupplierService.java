package com.celebrate.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.celebrate.backend.client.ViaCepClient;
import com.celebrate.backend.client.response.CepResponse;
import com.celebrate.backend.exception.InvalidClientDataException;
import com.celebrate.backend.exception.InvalidDataException;
import com.celebrate.backend.models.Address;
import com.celebrate.backend.models.Ceremonialist;
import com.celebrate.backend.models.Supplier;
import com.celebrate.backend.models.dto.CreateSupplier;
import com.celebrate.backend.models.dto.GetSupplier;
import com.celebrate.backend.repository.AddressRepository;
import com.celebrate.backend.repository.CeremonialistRepository;
import com.celebrate.backend.repository.SupplierRepository;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final AddressRepository addressRepository;
    private final ViaCepClient viaCepClient;
    private final CeremonialistRepository ceremonialistRepository;

    public SupplierService(SupplierRepository supplierRepository, 
                           AddressRepository addressRepository, 
                           ViaCepClient viaCepClient, 
                           CeremonialistRepository ceremonialistRepository) {
        this.supplierRepository = supplierRepository;
        this.addressRepository = addressRepository;
        this.viaCepClient = viaCepClient;
        this.ceremonialistRepository = ceremonialistRepository;
    }

    public List<GetSupplier> getAllSupplier(Integer idCeremonialist) {
        List<Supplier> suppliers = supplierRepository.findAllByCeremonialistId(idCeremonialist);
        List<GetSupplier> response = new ArrayList<>();
        suppliers.forEach(supplier -> response.add(new GetSupplier(supplier.getName(), supplier.getEmail(), supplier.getPhone(), supplier.getCnpj(), supplier.getAddress().getStreet(), supplier.getAddress().getHouseNumber(), supplier.getServiceType(), supplier.getDescription(), supplier.getImageUrl(), supplier.getId())));
        return response;
    }

    public void createSupplier(CreateSupplier request) {
        validateSupplierData(request);

        Ceremonialist ceremonialist = ceremonialistRepository.findByEmail(request.getCeremonialistEmail())
            .orElseThrow(() -> new InvalidClientDataException("Ceremonialista não encontrado"));

        Address address = createOrUpdateAddress(null, request);

        Supplier supplier = buildSupplier(request, ceremonialist, address);
        supplierRepository.save(supplier);
    }

    public void updateSupplierById(Integer supplierId, CreateSupplier request) {
        validateSupplierData(request);

        Supplier supplier = supplierRepository.findById(supplierId)
            .orElseThrow(() -> new InvalidDataException("Fornecedor não encontrado"));

        Address updatedAddress = createOrUpdateAddress(supplier.getAddress(), request);

        updateSupplierData(supplier, request, updatedAddress);
        supplierRepository.save(supplier);
    }

    // Métodos auxiliares

    private void validateSupplierData(CreateSupplier request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new InvalidDataException("O nome do fornecedor é obrigatório.");
        }
        if (!isValidEmail(request.getEmail())) {
            throw new InvalidDataException("Email inválido.");
        }
        if (!isValidCNPJ(request.getCnpj())) {
            throw new InvalidDataException("CNPJ inválido.");
        }
        if (!isValidPhoneNumber(request.getPhone())) {
            throw new InvalidDataException("Número de telefone inválido.");
        }
        if (request.getCep() == null || !request.getCep().matches("\\d{8}")) {
            throw new InvalidDataException("CEP inválido.");
        }
        if (request.getImageUrl() == null || request.getImageUrl().isBlank()) {
            throw new InvalidDataException("Imagem obrigatória");
        }
    }

    private Address createOrUpdateAddress(Address existingAddress, CreateSupplier request) {
        CepResponse cepResponse = viaCepClient.getAddressByCep(request.getCep());
        if (cepResponse == null) {
            throw new InvalidDataException("CEP inválido ou não encontrado.");
        }

        Address address = (existingAddress == null) ? new Address() : existingAddress;
        address.setCep(request.getCep());
        address.setState(cepResponse.getEstado());
        address.setCity(cepResponse.getLocalidade());
        address.setDistrict(cepResponse.getBairro());
        address.setStreet(cepResponse.getLogradouro());
        address.setHouseNumber(request.getHouseNumber());

        return addressRepository.save(address);
    }

    private Supplier buildSupplier(CreateSupplier request, Ceremonialist ceremonialist, Address address) {
        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setEmail(request.getEmail());
        supplier.setCnpj(request.getCnpj());
        supplier.setPhone(request.getPhone());
        supplier.setServiceType(request.getServiceType());
        supplier.setDescription(request.getDescription());
        supplier.setImageUrl(request.getImageUrl());
        supplier.setCeremonialist(ceremonialist);
        supplier.setAddress(address);
        return supplier;
    }

    private void updateSupplierData(Supplier supplier, CreateSupplier request, Address updatedAddress) {
        supplier.setName(request.getName());
        supplier.setEmail(request.getEmail());
        supplier.setCnpj(request.getCnpj());
        supplier.setPhone(request.getPhone());
        supplier.setServiceType(request.getServiceType());
        supplier.setDescription(request.getDescription());
        supplier.setImageUrl(request.getImageUrl());
        supplier.setAddress(updatedAddress);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }

    private boolean isValidCNPJ(String cnpj) {
        return cnpj != null && cnpj.length() == 14 && cnpj.chars().allMatch(Character::isDigit);
    }

    private boolean isValidPhoneNumber(String phone) {
        String phoneRegex = "^\\+?[1-9][0-9]{1,14}$";
        return Pattern.matches(phoneRegex, phone);
    }
}
