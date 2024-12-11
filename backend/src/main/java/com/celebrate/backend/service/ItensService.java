package com.celebrate.backend.service;

import org.springframework.stereotype.Service;

import com.celebrate.backend.models.Item;
import com.celebrate.backend.models.Supplier;
import com.celebrate.backend.models.Dto.CreateItem;
import com.celebrate.backend.repository.ItensRepository;
import com.celebrate.backend.repository.SupplierRepository;

@Service
public class ItensService {
    
    ItensRepository itensRepository;
    SupplierRepository supplierRepository;

    public ItensService(ItensRepository itensRepository, SupplierRepository supplierRepository){

        this.itensRepository = itensRepository;
        this.supplierRepository = supplierRepository;
    }

    public void createItem(CreateItem request){

        Supplier supplier = supplierRepository.findByCnpj(request.getSupplierCnpj())
            .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        Item item = new Item();

        item.setTitle(request.getTitle());
        item.setDescription(request.getDescription());
        item.setPrice(request.getPrice());

        item.setSupplier(supplier);

        //Ainda será preciso criar o orçamento para terminar de criar o item.
        //Discutir sobre a necessidade de associar o Orçamento ao Item.
        item.setBudget(null);

        itensRepository.save(item);
    }
}
