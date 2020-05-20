package com.utn.TPFinal.model.projections;

import java.util.Date;

public interface MobileReportUserCalls {
    String getLine();
    String getDestination();
    Date getDate();
    double getMinDuration();
    double getMinPrice();
    double getTotalAmount();
}
