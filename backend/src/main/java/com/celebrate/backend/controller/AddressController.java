package com.celebrate.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celebrate.backend.models.Address;
import com.celebrate.backend.service.AddressService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/address")
@RestController
public class AddressController {

    private final AddressService addressService;

    @PostMapping()
    public void addAddress(@RequestBody Address address) {
        addressService.createAddress(address);
    }

    @GetMapping()
    public List<Address> getAllAddress() {
        return addressService.getAllAddress();
    }

    @GetMapping("{id}")
    public Address getAddressById(@PathVariable Integer id) {
        return addressService.getAddressById(id);
    }

    @DeleteMapping("{id}")
    public void deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
    }

    @PutMapping("{id}")
    public void updateAddress(@PathVariable Integer id, @RequestBody Address address) {
        addressService.updateAddress(id, address);
    }

}
