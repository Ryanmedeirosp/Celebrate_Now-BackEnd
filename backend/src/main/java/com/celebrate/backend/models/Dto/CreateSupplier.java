package com.celebrate.backend.models.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSupplier {
    
    public String name;
    public String email;
    public String cnpj;
    public String phone;
    public String cep;
    public String serviceType;
    public String houseNumber;
    public String description;
}
