package com.celebrate.backend.controller;


import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celebrate.backend.models.dto.CreateSupplier;
import com.celebrate.backend.models.dto.GetSupplier;
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
    public List<GetSupplier> getAllSuppliers(@PathVariable Integer idCeremonialist) {
        return supplierService.getAllSupplier(idCeremonialist);
    }

    @PutMapping("/{ceremonialistId}")
    public void updateCeremonialisById(@PathVariable Integer ceremonialistId, @RequestBody CreateSupplier request){

        supplierService.updateSupplierById(ceremonialistId, request);
    }
}
