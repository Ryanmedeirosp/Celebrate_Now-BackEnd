package com.celebrate.backend.service;

import org.springframework.stereotype.Service;

import com.celebrate.backend.client.ViaCepClient;
import com.celebrate.backend.client.response.CepResponse;
import com.celebrate.backend.models.Address;
import com.celebrate.backend.models.Ceremonialist;
import com.celebrate.backend.models.Client;
import com.celebrate.backend.models.Item;
import com.celebrate.backend.models.Supplier;
import com.celebrate.backend.models.Dto.CreateSupplier;
import com.celebrate.backend.models.Dto.GetClients;
import com.celebrate.backend.models.Dto.GetSupplier;
import com.celebrate.backend.repository.AddressRepository;
import com.celebrate.backend.repository.CeremonialistRepository;
import com.celebrate.backend.repository.SupplierRepository;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final AddressRepository addressRepository;
    private final ViaCepClient viaCepClient;
     private final CeremonialistRepository ceremonialistRepository;

    public SupplierService(SupplierRepository supplierRepository, AddressRepository addressRepository, ViaCepClient viaCepClient, CeremonialistRepository ceremonialistRepository) {
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
        Address address = getAddressByCep(request);
        Supplier supplier = addDataToSupplier(request);

        supplier.setAddress(address);
        
        supplierRepository.save(supplier);
    }

    public void updateSupplierByCnpj(String cnpj, CreateSupplier request){

        Supplier supplier = supplierRepository.findByCnpj(request.getCnpj())
            .orElseThrow(()-> new RuntimeException());

        Address address = getAddressByCep(request);

        supplier.setAddress(address);

        updateSupplierData(supplier, request);

        supplierRepository.save(supplier);
    }

    private Supplier addDataToSupplier(CreateSupplier request) {
        Supplier supplier = new Supplier();

        Ceremonialist ceremonialist = ceremonialistRepository.findByEmail(request.getCeremonialistEmail())
        .orElseThrow(()-> new RuntimeException());

        supplier.setName(request.getName());
        supplier.setEmail(request.getEmail());
        supplier.setCnpj(request.getCnpj());
        supplier.setPhone(request.getPhone());
        supplier.setServiceType(request.getServiceType());
        supplier.setDescription(request.getDescription());
        supplier.setCeremonialist(ceremonialist);

        return supplier;
    }

    private void updateSupplierData(Supplier supplier, CreateSupplier request){

        supplier.setName(request.getName());
        supplier.setEmail(request.getEmail());
        supplier.setCnpj(request.getCnpj());
        supplier.setPhone(request.getPhone());
        supplier.setServiceType(request.getServiceType());
        supplier.setDescription(request.getDescription());
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