package com.celebrate.backend.models.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateContract {
    
    public Integer idBudget;
    public String pdf;
}
