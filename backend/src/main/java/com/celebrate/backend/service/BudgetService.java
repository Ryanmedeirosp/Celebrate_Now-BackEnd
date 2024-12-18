package com.celebrate.backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.celebrate.backend.models.Budget;
import com.celebrate.backend.models.Client;
import com.celebrate.backend.models.Contract;
import com.celebrate.backend.models.Item;
import com.celebrate.backend.models.Supplier;
import com.celebrate.backend.models.dto.AddItemsToBudget;
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
    private final ItensRepository itensRepository;

    public BudgetService(BudgetRepository budgetRepository, ClientRepository clientRepository, SupplierRepository supplierRepository, ItensRepository itensRepository){

        this.budgetRepository = budgetRepository;
        this.clientRepository = clientRepository;
        this.supplierRepository = supplierRepository;
        this.itensRepository = itensRepository;
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

    public List<GetBudget> getBudgets(Integer clientId){
        List<Budget> budgets = budgetRepository.findAllByClientId(clientId);
        List<GetBudget> response = new ArrayList<>();
        for (Budget budget : budgets) {
            if(budget.getContract().getContract_number() != null){

                response.add(new GetBudget(budget.getSupplier().getName(), 
                budget.getClient().getName(), budget.getBuget_date(), 
                budget.getItems(), budget.getContract().getContract_number()));

            }else{
                response.add(new GetBudget(budget.getSupplier().getName(), 
                budget.getClient().getName(), budget.getBuget_date(), 
                budget.getItems(), UUID.fromString("0")));
            }
           
        }
        return response; 
    }

    public void addItemsToBudget(AddItemsToBudget request){

        Budget budget = budgetRepository.findById(request.getBudgetId())
            .orElseThrow(() -> new RuntimeException("Orçamento não encontrado."));
        List<Item> itensBudget = budget.getItems();
        for(Integer id : request.getItemsId()){

            Item item = itensRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado."));

            itensBudget.add(item);
        }
        budget.setItems(itensBudget);
        budgetRepository.save(budget);
    }
}
