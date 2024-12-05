package com.celebrate.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.celebrate.backend.models.Ceremonialist;
import com.celebrate.backend.repository.AddressRepository;
import com.celebrate.backend.repository.CeremonialistRepository;

@Service
public class CeremonialistService {
    private final CeremonialistRepository ceremonialistRepository;
    private final AddressRepository addressRepository;

    public CeremonialistService(CeremonialistRepository ceremonialistRepository, AddressRepository addressRepository) {
        this.ceremonialistRepository = ceremonialistRepository;
        this.addressRepository = addressRepository;
    }

    public List<Ceremonialist> getAllCeremonialists() {
        return ceremonialistRepository.findAll();
    }

    public Ceremonialist getCeremonialistById(Integer id) {
        return ceremonialistRepository.findById(id).orElse(null);
    }

    public Ceremonialist createCeremonialist(Ceremonialist ceremonialist) {
        return ceremonialistRepository.save(ceremonialist);
    }

    

}
