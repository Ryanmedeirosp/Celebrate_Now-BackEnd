package com.celebrate.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celebrate.backend.models.dto.AddItemsToBudget;
import com.celebrate.backend.models.dto.CreateBudget;
import com.celebrate.backend.models.dto.GetBudget;
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

    @GetMapping({"{idClient}"})
    public List<GetBudget> getBudget(@PathVariable Integer idClient) {
        return budgetService.getBudgets(idClient);
    }

    @PostMapping("/addItems")
    public void addItemsToBudget(@RequestBody AddItemsToBudget request){

        budgetService.addItemsToBudget(request);
    }
}
