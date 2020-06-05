package com.utn.TPFinal.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CallInput {
    private Integer numberFrom;
    private Integer numberTo;
    private double duration;
    private Date callDate;
}
