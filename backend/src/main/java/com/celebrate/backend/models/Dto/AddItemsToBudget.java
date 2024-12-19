package com.celebrate.backend.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItemsToBudget {

    private Integer budgetId;
    private List<Integer> itemsId;
}
