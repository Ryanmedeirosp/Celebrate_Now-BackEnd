package com.celebrate.backend.models.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetCeremonialist {
    Integer id;
    String name;
    String email;
}
