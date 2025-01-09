package com.celebrate.backend.models.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.celebrate.backend.models.Item;

import lombok.Data;


@Data
public class GetBudget {
    private String supplier;
    private String client;
    private LocalDate date;
    private List<Item> items;
    private UUID contract;
    private BigDecimal totalAmount;

    public GetBudget(String supplier, String client, LocalDate date, List<Item> items, UUID contract, BigDecimal totalAmount) {
        this.supplier = supplier;
        this.client = client;
        this.date = date;
        this.items = items;
        this.contract = contract;
        this.totalAmount = totalAmount;
    }

}
