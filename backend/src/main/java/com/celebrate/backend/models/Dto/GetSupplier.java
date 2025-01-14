package com.celebrate.backend.models.dto;

import lombok.Data;

@Data
public class GetSupplier {
    private String name;
    private String email;
    private String phone;
    private String cnpj;

    public GetSupplier(String name, String email, String phone, String cnpj) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cnpj = cnpj;
    }

}
