package com.celebrate.backend.models.dto;

import lombok.Data;

@Data
public class GetClients {

    private String name;
    private String email;
    private String phone;
    private Integer clientId;

    public GetClients(String name, String email, String phone, Integer clientId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.clientId = clientId;
    }

}
