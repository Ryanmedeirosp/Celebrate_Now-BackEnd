package com.celebrate.backend.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.celebrate.backend.models.Client;
import com.celebrate.backend.models.Contract;
import com.celebrate.backend.models.Dto.CreateContract;
import com.celebrate.backend.repository.ClientRepository;
import com.celebrate.backend.repository.ContractRepository;

@Service
public class ContractService {
    
    private final ContractRepository contractRepository;
    private final ClientRepository clientRepository;

    public ContractService(ContractRepository contractRepository, ClientRepository clientRepository){

        this.contractRepository = contractRepository;
        this.clientRepository = clientRepository;
    }

    public void createContract(CreateContract request){

        Contract contract = new Contract();

        Client client = clientRepository.findByEmail(request.getClientEmail())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        contract.setPdf(request.getPdf());

        //Conversar sobre uma forma de identificar o orçamento a que o contrato se refere.
        contract.setBudget(client.getBudget().get(0));

        contract.setContract_number(generateRandomNumber());

        contractRepository.save(contract);
    }

    public Integer generateRandomNumber(){

        Random random = new Random();

        Integer number = random.nextInt(1000000) + 1;

        return number;
    }
}
