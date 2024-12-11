package com.celebrate.backend.models.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateItem {
    
    public String title;
    public String description;
    public BigDecimal price;
    public Integer budgetId;
    
}
