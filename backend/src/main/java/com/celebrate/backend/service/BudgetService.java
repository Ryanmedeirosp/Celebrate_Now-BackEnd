package com.celebrate.backend.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.celebrate.backend.models.Budget;
import com.celebrate.backend.models.Client;
import com.celebrate.backend.models.Contract;
import com.celebrate.backend.models.Item;
import com.celebrate.backend.models.Supplier;
import com.celebrate.backend.models.dto.CreateBudget;
import com.celebrate.backend.models.dto.GetBudget;
import com.celebrate.backend.repository.BudgetRepository;
import com.celebrate.backend.repository.ClientRepository;
import com.celebrate.backend.repository.ItensRepository;
import com.celebrate.backend.repository.SupplierRepository;

@Service
public class BudgetService {
    
    private final BudgetRepository budgetRepository;
    private final ClientRepository clientRepository;
    private final SupplierRepository supplierRepository;

    public BudgetService(BudgetRepository budgetRepository, ClientRepository clientRepository, SupplierRepository supplierRepository, ItensRepository itensRepository){

        this.budgetRepository = budgetRepository;
        this.clientRepository = clientRepository;
        this.supplierRepository = supplierRepository;
    }


    public Budget createBudget(CreateBudget request){

        Budget budget = new Budget();

        Client client = clientRepository.findByEmail(request.getClientEmail())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Supplier supplier = supplierRepository.findByCnpj(request.getSupplierCnpj())
            .orElseThrow(() -> new RuntimeException("Cnpj não encontrado"));
            
        //Discutir isso aqui também.
        Contract contract = null;

        budget.setBuget_date(LocalDate.now());
        budget.setClient(client);
        budget.setSupplier(supplier);
        budget.setContract(contract);

        budget = budgetRepository.save(budget);
        

        return budget;
    }   

    public List<GetBudget> getBudgets(Integer clientId, Integer ceremonialistId) {
        List<Budget> budgets = budgetRepository.findAllByClientId(clientId);
        List<GetBudget> response = new ArrayList<>();
        
        for (Budget budget : budgets) {
            if(budget.getClient().getCeremonialist().getId() != ceremonialistId) {
                new RuntimeException();
            }
            
             BigDecimal totalAmount = budget.getItems().stream()
                                       .map(Item::getPrice) // Substitua "getPrice" pelo nome real do método que retorna o preço.
                                       .reduce(BigDecimal.ZERO, BigDecimal::add);
    
            // Adicionar ao response com contrato válido ou UUID padrão
            if (budget.getContract() != null && budget.getContract().getContract_number() != null) {
                response.add(new GetBudget(
                    budget.getSupplier().getName(),
                    budget.getClient().getName(),
                    budget.getBuget_date(),
                    budget.getClient().getCeremonialist().getId(),
                    budget.getItems(),
                    totalAmount,
                    budget.getId()
                ));
            } else {
                response.add(new GetBudget(
                    budget.getSupplier().getName(),
                    budget.getClient().getName(),
                    budget.getBuget_date(),
                    budget.getClient().getCeremonialist().getId(),
                    budget.getItems(),
                    totalAmount,
                    budget.getId()
                ));
            }
        }
    
        return response;
    }
    
    public GetBudget getBudgetById(Integer id) {
        Budget budget = budgetRepository.findById(id).orElse(null);
        if (budget == null) {
            throw new RuntimeException("Budget not found");
        }

        BigDecimal totalAmount = budget.getItems().stream()
        .map(Item::getPrice) // Substitua "getPrice" pelo nome real do método que retorna o preço.
        .reduce(BigDecimal.ZERO, BigDecimal::add);


        return new GetBudget(
                budget.getSupplier().getName(),
                budget.getClient().getName(),
                budget.getBuget_date(),
                budget.getClient().getCeremonialist().getId(),
                budget.getItems(),
                totalAmount,
                budget.getId()
                );
        }

}
