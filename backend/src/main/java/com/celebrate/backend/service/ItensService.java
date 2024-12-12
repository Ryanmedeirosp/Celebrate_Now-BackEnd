package com.celebrate.backend.service;

import org.springframework.stereotype.Service;

import com.celebrate.backend.models.Budget;
import com.celebrate.backend.models.Item;
import com.celebrate.backend.models.Supplier;
import com.celebrate.backend.models.Dto.CreateItem;
import com.celebrate.backend.repository.BudgetRepository;
import com.celebrate.backend.repository.ItensRepository;
import com.celebrate.backend.repository.SupplierRepository;

@Service
public class ItensService {
    
    ItensRepository itensRepository;
    SupplierRepository supplierRepository;
    BudgetRepository budgetRepository;

    public ItensService(ItensRepository itensRepository, SupplierRepository supplierRepository, BudgetRepository budgetRepository) {

        this.itensRepository = itensRepository;
        this.supplierRepository = supplierRepository;
        this.budgetRepository = budgetRepository;
    }

    public void createItem(CreateItem request){

        Budget budget = budgetRepository.findById(request.getBudgetId())
            .orElseThrow(() -> new RuntimeException("Não encontrado"));

        Item item = new Item();
        item.setBudget(budget);
        item.setTitle(request.getTitle());
        item.setDescription(request.getDescription());
        item.setPrice(request.getPrice());
        

        itensRepository.save(item);
    }
}
