package com.celebrate.backend.models.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetContract {
    private UUID contractId;
    private String pdf;
    private String clientName;
    private String ceremonialistName;
    private String supplierName;

    

}
