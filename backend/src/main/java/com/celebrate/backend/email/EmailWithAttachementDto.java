package com.celebrate.backend.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailWithAttachementDto {
    String to;
    String subject;
    String message;
    String pdfBase64;
}
