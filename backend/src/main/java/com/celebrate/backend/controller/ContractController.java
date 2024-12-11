package com.celebrate.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celebrate.backend.models.Dto.CreateContract;
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
}
