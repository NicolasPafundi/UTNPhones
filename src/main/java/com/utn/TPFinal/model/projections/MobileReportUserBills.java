package com.utn.TPFinal.model.projections;

import java.util.Date;

public interface MobileReportUserBills {
    Integer getBillNumber();
    String getLineNumber();
    Integer getCallsAmount();
    double getCostPrice();
    double getTotalPrice();
    Date getCreationDay();
    Date getPayDay();
}
