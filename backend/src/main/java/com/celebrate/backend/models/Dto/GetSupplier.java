package com.celebrate.backend.models.dto;

import lombok.Data;

@Data
public class GetSupplier {
    private String name;
    private String email;
    private String phone;
    private String cnpj;
    private String street;

    public GetSupplier(String name, String email, String phone, String cnpj, String street) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.street = street;
        this.cnpj = cnpj;
    }

}
