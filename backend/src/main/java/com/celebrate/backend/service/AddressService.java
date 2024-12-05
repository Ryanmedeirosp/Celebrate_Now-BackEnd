package com.celebrate.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.celebrate.backend.models.Address;
import com.celebrate.backend.repository.AddressRepository;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Integer id) {
        return addressRepository.findById(id).orElse(null);
    }

    public void createAddress(Address address){
        address.setSuppliers(new ArrayList<>());
        address.setCerimonialists(new ArrayList<>());
        address.setClients(new ArrayList<>());
        addressRepository.save(address);
    }

    public void deleteAddress(Integer id){
        addressRepository.deleteById(id);
    }

    public void updateAddress(Integer id, Address address){
        Address existingAddress = addressRepository.findById(id).orElse(null);
        if (existingAddress != null) {
            existingAddress.setStreet(address.getStreet());
            existingAddress.setCity(address.getCity());
            existingAddress.setState(address.getState());
            existingAddress.setCep(address.getCep());
            existingAddress.setDistrict(address.getDistrict());
            existingAddress.setHouseNumber(address.getHouseNumber());
            existingAddress.setSuppliers(new ArrayList<>());
            existingAddress.setCerimonialists(new ArrayList<>());
            existingAddress.setClients(new ArrayList<>());
        }

        addressRepository.save(existingAddress);
    }

    
}
