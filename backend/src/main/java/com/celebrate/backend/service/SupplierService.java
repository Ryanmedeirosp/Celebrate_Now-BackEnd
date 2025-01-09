package com.celebrate.backend.service;

import java.util.ArrayList;
import java.util.List;

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
        for (Supplier supplier : suppliers) {
            response.add(new GetSupplier(supplier.getName(), supplier.getEmail(), supplier.getPhone()));
        }
        return response;
    }

    public void createSupplier(CreateSupplier request) {
        validateSupplierData(request);

        Ceremonialist ceremonialist = ceremonialistRepository.findByEmail(request.getCeremonialistEmail())
            .orElseThrow(() -> new InvalidClientDataException("Ceremonialista não encontrado"));

        Address address = getAddressByCep(request);

        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setEmail(request.getEmail());
        supplier.setCnpj(request.getCnpj());
        supplier.setPhone(request.getPhone());
        supplier.setServiceType(request.getServiceType());
        supplier.setDescription(request.getDescription());
        supplier.setCeremonialist(ceremonialist);
        supplier.setAddress(address);

        supplierRepository.save(supplier);
    }

    public void updateSupplierById(Integer supplierId, CreateSupplier request) {
        validateSupplierData(request);

        Supplier supplier = supplierRepository.findById(supplierId)
            .orElseThrow(() -> new InvalidDataException("Fornecedor não encontrado"));

        supplier.setName(request.getName());
        supplier.setEmail(request.getEmail());
        supplier.setCnpj(request.getCnpj());
        supplier.setPhone(request.getPhone());
        supplier.setServiceType(request.getServiceType());
        supplier.setDescription(request.getDescription());

        updateAddress(supplier.getAddress(), request);

        supplierRepository.save(supplier);
    }

    private Address getAddressByCep(CreateSupplier request) {
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

    private void updateAddress(Address address, CreateSupplier request) {
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

    private void validateSupplierData(CreateSupplier request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new InvalidDataException("O nome do fornecedor é obrigatório.");
        }
        if (request.getEmail() == null || !request.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidDataException("Email inválido.");
        }
        if (request.getCnpj() == null || request.getCnpj().length() != 14) {
            throw new InvalidDataException("CNPJ inválido.");
        }
        if (request.getPhone() == null || !request.getPhone().matches("^\\+?[1-9][0-9]{1,14}$")) {
            throw new InvalidDataException("Número de telefone inválido.");
        }
    }
}
