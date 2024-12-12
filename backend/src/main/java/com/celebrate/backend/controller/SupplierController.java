package com.celebrate.backend.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celebrate.backend.models.Dto.CreateSupplier;
import com.celebrate.backend.models.Dto.GetSupplier;
import com.celebrate.backend.service.SupplierService;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    @PostMapping()
    public void createSupplier(@RequestBody CreateSupplier supplier) {
         supplierService.createSupplier(supplier);
    }

    @GetMapping("{idCeremonialist}")
    public List<GetSupplier> getAllClients(@PathVariable Integer idCeremonialist){
        return supplierService.getAllSupplier(idCeremonialist);
    }
}
