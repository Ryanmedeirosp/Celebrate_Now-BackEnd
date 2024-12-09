package com.celebrate.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.celebrate.backend.client.ViaCepClient;
import com.celebrate.backend.client.response.CepResponse;
import com.celebrate.backend.models.Address;
import com.celebrate.backend.models.Item;
import com.celebrate.backend.models.Supplier;
import com.celebrate.backend.models.Dto.CreateSupplier;
import com.celebrate.backend.repository.AddressRepository;
import com.celebrate.backend.repository.SupplierRepository;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final AddressRepository addressRepository;
    private final ViaCepClient viaCepClient;

    public SupplierService(SupplierRepository supplierRepository, AddressRepository addressRepository, ViaCepClient viaCepClient) {
        this.supplierRepository = supplierRepository;
        this.addressRepository = addressRepository;
        this.viaCepClient = viaCepClient;
    }

    public void createSupplier(CreateSupplier request) {
        Address address = getAddressByCep(request);
        Supplier supplier = addDataToSupplier(request);

        List<Item> items = new ArrayList<>();

        supplier.setAddress(address);
        supplier.setItens(items);

        supplierRepository.save(supplier);
    }

    private Supplier addDataToSupplier(CreateSupplier request) {
        Supplier supplier = new Supplier();

        supplier.setName(request.getName());
        supplier.setEmail(request.getEmail());
        supplier.setCnpj(request.getCnpj());
        supplier.setPhone(request.getPhone());
        supplier.setServiceType(request.getServiceType());
        supplier.setDescription(request.getDescription());

        return supplier;
    }

    private Address getAddressByCep(CreateSupplier request) {
        System.out.println(request.getCep());
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