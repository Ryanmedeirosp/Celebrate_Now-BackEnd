package com.celebrate.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name = "id_itens", referencedColumnName = "id")
    private Item item;

    @OneToOne(mappedBy = "budget")  
    private Contract contract;

    public Budget(LocalDate buget_date, Ceremonialist ceremonialist, Client client, Item item) {
        this.buget_date = buget_date;
        this.ceremonialist = ceremonialist;
        this.client = client;
        this.item = item;
    }
}
