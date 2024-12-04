package com.celebrate.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cep")
    private String cep;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @OneToMany(mappedBy = "address")
    private List<Ceremonialist> cerimonialists;

    @OneToMany(mappedBy = "address")
    private List<Client> clients;

    @OneToMany(mappedBy = "address")
    private List<Supplier> suppliers;

    public Address(String cep, String state, String city, String district, String street, String houseNumber) {
        this.cep = cep;
        this.state = state;
        this.city = city;
        this.district = district;
        this.street = street;
        this.houseNumber = houseNumber;
    }
}

