package com.celebrate.backend.models.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetItem {

    private BigDecimal price;
    private String title;
    private String description;
    private String supplierName;
    private Integer budget;


}
