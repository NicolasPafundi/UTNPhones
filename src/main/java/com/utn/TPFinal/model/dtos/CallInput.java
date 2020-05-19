package com.utn.TPFinal.domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CallInput {
    private String numberFrom;
    private String codeFrom;
    private String numberTo;
    private String codeTo;
    private double duration;
}
