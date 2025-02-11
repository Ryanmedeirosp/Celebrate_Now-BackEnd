package com.celebrate.backend.models.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


import com.celebrate.backend.models.Item;

import lombok.Data;


@Data
public class GetBudget {
    private String supplier;
    private String client;
    private String clientEmail;
    private LocalDate date;
    private List<Item> items;
    private Integer ceremonialistId;
    private BigDecimal totalAmount;
    private Integer budgetId;

    public GetBudget(String supplier, String client, String clientEmail, LocalDate date,Integer ceremonialistId, List<Item> items, BigDecimal totalAmount, Integer budgetId) {
        this.supplier = supplier;
        this.client = client;
        this.clientEmail = clientEmail;
        this.date = date;
        this.ceremonialistId = ceremonialistId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.budgetId = budgetId;
    }

}
