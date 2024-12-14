package com.celebrate.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celebrate.backend.models.dto.CreateBudget;
import com.celebrate.backend.service.BudgetService;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/budget")
public class BudgetController {
    
    private final BudgetService budgetService;

    @PostMapping()
    public void createBudget(@RequestBody CreateBudget request){

        budgetService.createBudget(request);
    }
}
