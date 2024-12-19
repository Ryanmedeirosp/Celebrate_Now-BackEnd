package com.celebrate.backend.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.celebrate.backend.models.Budget;
import com.celebrate.backend.models.Item;
import com.celebrate.backend.models.dto.CreateItem;
import com.celebrate.backend.models.dto.GetItem;
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

    public List<GetItem> getAllItems(Integer idBudget) {
        budgetRepository.findById(idBudget).orElseThrow(()-> new RuntimeException("Orçamento não encontrada"));
       
        List<Item> itens = itensRepository.findAllByBudgetId(idBudget);
        List<GetItem> response = new ArrayList<>();
        for (Item item : itens) {
          
            response.add(new GetItem(item.getPrice(),item.getTitle(), item.getDescription(), item.getBudget().getSupplier().getName(), item.getBudget().getId()));    
        }
        return response; 
    }
}
