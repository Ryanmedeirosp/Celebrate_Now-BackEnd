package com.celebrate.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBudget {
    
    public String clientEmail;
    public String supplierCnpj;
}
