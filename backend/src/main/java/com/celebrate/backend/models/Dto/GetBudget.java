package com.celebrate.backend.models.dto;

import java.time.LocalDate;
import java.util.List;

import com.celebrate.backend.models.Contract;
import com.celebrate.backend.models.Item;

import lombok.Data;


@Data
public class GetBudget {
    private String supplier;
    private String client;
    private LocalDate date;
    private List<Item> items;
    private Contract contract;

    public GetBudget(String supplier, String client, LocalDate date, List<Item> items, Contract contract) {
        this.supplier = supplier;
        this.client = client;
        this.date = date;
        this.items = items;
        this.contract = contract;
    }

}
