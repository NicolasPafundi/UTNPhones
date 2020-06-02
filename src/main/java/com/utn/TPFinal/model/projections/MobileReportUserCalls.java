package com.utn.TPFinal.model.projections;

import java.util.Date;

public interface MobileReportUserCalls {
    String getLine();
    String getDestination();
    Date getDate();
    double getMinDuration();
    double getMinPrice();
    double getTotalAmount();

    void setLine(String line);
    void setDestination(String destination);
    void setDate(Date date);
    void setMinDuration(double minDuration);
    void setMinPrice(double minPrice);
    void setTotalAmount(double totalAmount);
}
