package com.utn.TPFinal.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MobileReportFilter {
    private Date dateFrom;
    private Date dateTo;
}
