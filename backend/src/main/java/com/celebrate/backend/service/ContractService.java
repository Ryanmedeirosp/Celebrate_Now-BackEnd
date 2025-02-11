package com.celebrate.backend.service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.celebrate.backend.models.Budget;
import com.celebrate.backend.models.Contract;
import com.celebrate.backend.models.dto.CreateContract;
import com.celebrate.backend.models.dto.GetContract;
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

        if(budget.getContract() != null){
            throw new RuntimeException("Orçamento já tem um contrato");
        }

        contract.setPdf(request.getPdf());

       
        contract.setBudget(budget);

        contract.setContract_number(UUID.randomUUID());

        contractRepository.save(contract);
    }

    public List<GetContract> getContracts(Integer idBudget){
        budgetRepository.findById(idBudget).orElseThrow(()-> new RuntimeException("Orçamento não encontrada"));

        Budget budget = budgetRepository.findById(idBudget)
            .orElseThrow(() -> new RuntimeException("Orçamento não encontrada"));

        String clientEmail = budget.getClient().getEmail();

        List<Contract> contracts = contractRepository.findAllByBudgetId(idBudget);
        List<GetContract> response = new ArrayList<>();
        for (Contract contract : contracts) {
          
                response.add(new GetContract(contract.getContract_number(),
                 contract.getPdf(),
                 contract.getBudget().getClient().getName(),
                 clientEmail,
                 contract.getBudget().getClient().getCeremonialist().getName(),
                 contract.getBudget().getSupplier().getName(), contract.getBudget().getBuget_date()));
            
           
        }
        return response; 
    }



}
