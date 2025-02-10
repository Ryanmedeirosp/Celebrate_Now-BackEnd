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

    public GetSupplier(String name, String email, String phone, String cnpj, String street, String number, String serviceType, String description, String imageUrl, Integer id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.street = street;
        this.cnpj = cnpj;
        this.number = number;
        this.description = description;
        this.imageUrl = imageUrl;
        this.serviceType = serviceType;
        this.id = id;
    }

}
