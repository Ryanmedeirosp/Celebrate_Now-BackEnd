package com.celebrate.backend.models.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClient {
    
    public String name;
    public String email;
    public String password;
    public String cpf;
    public LocalDate birthday;
    public String phone;
    public String cep;
    public String houseNumber;
    public String ceremonialistEmail;
}
