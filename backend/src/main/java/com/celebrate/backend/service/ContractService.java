package com.celebrate.backend.service;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.celebrate.backend.models.Budget;
import com.celebrate.backend.models.Contract;
import com.celebrate.backend.models.Dto.CreateContract;
import com.celebrate.backend.repository.BudgetRepository;
import com.celebrate.backend.repository.ContractRepository;

@Service
public class ContractService {
    
    private final ContractRepository contractRepository;
    private final BudgetRepository budgetRepository;

    public ContractService(ContractRepository contractRepository,  BudgetRepository budgetRepository) {

        this.contractRepository = contractRepository;
        this.budgetRepository = budgetRepository;
    }

    public void createContract(CreateContract request){

        Contract contract = new Contract();
        
        Budget budget = budgetRepository.findById(request.getIdBudget()).orElseThrow(()-> new RuntimeException("Orçamento não encontrado"));

        contract.setPdf(request.getPdf());

       
        contract.setBudget(budget);

        contract.setContract_number(UUID.randomUUID());

        contractRepository.save(contract);
    }


}
