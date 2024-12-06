package com.celebrate.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.celebrate.backend.client.ViaCepClient;
import com.celebrate.backend.models.Ceremonialist;
import com.celebrate.backend.models.Dto.CreateCeremonialist;
import com.celebrate.backend.repository.CeremonialistRepository;

@Service
public class CeremonialistService {
    private final CeremonialistRepository ceremonialistRepository;
    private final ViaCepClient viaCepClient;

    public CeremonialistService(CeremonialistRepository ceremonialistRepository, ViaCepClient viaCepClient) {
        this.ceremonialistRepository = ceremonialistRepository;
        this.viaCepClient = viaCepClient;
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

    public void createCeremonialistTest(CreateCeremonialist request){

        Ceremonialist ceremonialist = new Ceremonialist();

        ceremonialist.setName(request.getName());
        ceremonialist.setEmail(request.getEmail());
        ceremonialist.setPassword(request.getPassword());
        ceremonialist.setDocument(request.getDocument());
        ceremonialist.setBirthday(request.getBirthday());
        ceremonialist.setPhone(request.getPhone());

        System.out.println(viaCepClient.getAddressByCep(request.getCep()));
    }

    public void getAddressByCep(CreateCeremonialist request){

        System.out.println(viaCepClient.getAddressByCep(request.getCep()));
    }
}
