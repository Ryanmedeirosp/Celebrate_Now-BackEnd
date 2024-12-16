package com.celebrate.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celebrate.backend.models.dto.CreateContract;
import com.celebrate.backend.models.dto.GetContract;
import com.celebrate.backend.service.ContractService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contract")
public class ContractController {
    
    private final ContractService contractService;

    @PostMapping()
    public void createBudget(@RequestBody CreateContract request){

        contractService.createContract(request);
    }

    @GetMapping("{budgetId}")
    public List<GetContract> getContract(@PathVariable Integer budgetId) {
        return contractService.getContracts(budgetId);
    }

}
