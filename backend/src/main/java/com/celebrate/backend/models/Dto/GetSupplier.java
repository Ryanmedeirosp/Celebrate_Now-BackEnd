package com.celebrate.backend.models.dto;

import lombok.Data;

@Data
public class GetSupplier {
    private String name;
    private String email;
    private String phone;
    private String cnpj;
        private String serviceType;
                private String description;
            
    public GetSupplier(String name, String email, String phone, String cnpj, String serviceType, String description) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cnpj = cnpj;
        this.serviceType = serviceType;
        this.description = description;
    }

}
