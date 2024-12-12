package com.celebrate.backend.models;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contract")
@Data
@NoArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pdf", nullable = false, columnDefinition = "TEXT")
    private String pdf;

    @Column(name="contract_number", nullable = false)
    private UUID contract_number;

    @OneToOne
    @JoinColumn(name = "id_budget", referencedColumnName = "id")
    private Budget budget;

    public Contract(String pdf, Budget budget) {
        this.pdf = pdf;
        this.budget = budget;
    }
}
