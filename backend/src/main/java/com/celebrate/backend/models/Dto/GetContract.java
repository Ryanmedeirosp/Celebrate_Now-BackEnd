package com.celebrate.backend.models.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class GetContract {
    private UUID contractId;
    private String pdf;
    private Boolean signed;
    private String clientName;
    private String clientEmail;
    private String ceremonialistName;
    private String supplierName;
    private LocalDate date;

    public GetContract(UUID contractId, String pdf, Boolean signed, String clientName, String clientEmail, String ceremonialistName, String supplierName,
            LocalDate date) {
        this.contractId = contractId;
        this.pdf = pdf;
        this.signed = signed;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.ceremonialistName = ceremonialistName;
        this.supplierName = supplierName;
        this.date = date;
    }

    

}
