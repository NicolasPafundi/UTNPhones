package com.utn.TPFinal.domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CallFilter {
    private Integer userId;
    private Date dateFrom;
    private Date dateTo;
}
