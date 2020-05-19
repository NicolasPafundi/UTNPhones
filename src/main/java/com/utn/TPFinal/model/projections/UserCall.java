package com.utn.TPFinal.domain.Projections;

import java.util.Date;

public interface UserCall {
    Integer getId();
    String getLine();
    String getDestination();
    Date getDate();
    double getMinDuration();
    double getMinPrice();
    double getTotalAmount();
}
