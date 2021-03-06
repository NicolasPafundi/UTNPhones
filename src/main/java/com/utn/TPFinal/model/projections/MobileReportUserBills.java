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

    void setBillNumber(Integer BillNumber);
    void setLineNumber(String LineNumber);
    void setCallsAmount(Integer CallsAmount);
    void setCostPrice(double CostPrice);
    void setTotalPrice(double TotalPrice);
    void setCreationDay(Date CreationDay);
    void setPayDay(Date PayDay);
}
