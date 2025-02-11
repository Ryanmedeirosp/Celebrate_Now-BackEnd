package com.celebrate.backend.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supplier")
@Data
@NoArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "cnpj", nullable = false, length = 14)
    private String cnpj;

    @Column(name = "phone", nullable = false, length = 25)
    private String phone;

    @Column(name = "service_type", nullable = false, length = 30)
    private String serviceType;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @OneToMany(mappedBy = "supplier")
    private List<Budget> budget;

    @ManyToOne
    @JoinColumn(name = "id_cerimonialist", referencedColumnName = "id")
    private Ceremonialist ceremonialist;

    @ManyToOne
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    private Address address;

    public Supplier(String name, String email, String cnpj, String phone, String serviceType, String description, Address address) {
        this.name = name;
        this.email = email;
        this.cnpj = cnpj;
        this.phone = phone;
        this.serviceType = serviceType;
        this.description = description;
        this.address = address;
    }
}
