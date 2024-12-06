package com.celebrate.backend.models.Dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCeremonialist {
    public String name;
    public String email;
    public String password;
    public String document;
    public LocalDate birthday;
    public String phone;

    public String cep;
}
