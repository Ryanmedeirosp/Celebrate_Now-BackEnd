package com.celebrate.backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.celebrate.backend.models.Budget;
import com.celebrate.backend.models.Client;
import com.celebrate.backend.models.Contract;
import com.celebrate.backend.models.Item;
import com.celebrate.backend.models.Supplier;
import com.celebrate.backend.models.Dto.CreateBudget;
import com.celebrate.backend.repository.BudgetRepository;
import com.celebrate.backend.repository.ClientRepository;
import com.celebrate.backend.repository.ItensRepository;
import com.celebrate.backend.repository.SupplierRepository;

@Service
public class BudgetService {
    
    private final BudgetRepository budgetRepository;
    private final ClientRepository clientRepository;
    private final SupplierRepository supplierRepository;

    public BudgetService(BudgetRepository budgetRepository, ClientRepository clientRepository, SupplierRepository supplierRepository){

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

        budgetRepository.save(budget);

        return budget;
    }   
}
