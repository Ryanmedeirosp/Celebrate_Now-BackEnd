package com.celebrate.backend.models.Dto;

import com.celebrate.backend.models.Client;

import lombok.Data;

@Data
public class GetClients {

    private String name;
    private String email;
    private String phone;

    public GetClients(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

}
