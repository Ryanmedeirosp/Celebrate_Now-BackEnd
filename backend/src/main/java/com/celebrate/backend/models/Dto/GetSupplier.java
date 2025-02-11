package com.celebrate.backend.models.dto;

import lombok.Data;

@Data
public class GetSupplier {
    private String name;
    private String email;
    private String phone;
    private String cnpj;
    private String street;
    private String number;
    private String serviceType;
    private String description;
    private String imageUrl;
    private Integer id;
    private String cep;

    public GetSupplier(String name, String email, String phone, String cnpj, String street, String number, String serviceType, String description, String imageUrl, Integer id, String cep) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cnpj = cnpj;
        this.street = street;
        this.number = number;
        this.serviceType = serviceType;
        this.description = description;
        this.imageUrl = imageUrl;
        this.id = id;
        this.cep = cep;
    }

}
