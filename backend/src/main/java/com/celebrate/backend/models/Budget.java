package com.celebrate.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "budget")
@Data
@NoArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "buget_date", nullable = false)
    private LocalDate buget_date;

    @ManyToOne
    @JoinColumn(name = "id_cerimonialist", referencedColumnName = "id")
    private Ceremonialist ceremonialist;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    private Client client;

    @OneToMany(mappedBy = "budget")  
    private List<Item> item;

    @OneToOne(mappedBy = "budget")  
    private Contract contract;

    public Budget(LocalDate buget_date, Ceremonialist ceremonialist, Client client) {
        this.buget_date = buget_date;
        this.ceremonialist = ceremonialist;
        this.client = client;
    }
}
