package com.utn.TPFinal.Domain.DTOs;

import com.utn.TPFinal.Domain.Entities.PhoneLine;
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
