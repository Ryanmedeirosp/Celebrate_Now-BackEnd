package com.celebrate.backend.models.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class GetContract {
    private UUID contractId;
    private String pdf;
    private String clientName;
    private String ceremonialistName;
    private String supplierName;
    private LocalDate date;

    public GetContract(UUID contractId, String pdf, String clientName, String ceremonialistName, String supplierName,
            LocalDate date) {
        this.contractId = contractId;
        this.pdf = pdf;
        this.clientName = clientName;
        this.ceremonialistName = ceremonialistName;
        this.supplierName = supplierName;
        this.date = date;
    }

    

}
