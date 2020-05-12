package com.utn.TPFinal.Domain.Projections;

import java.util.Date;

public interface UserCall {
    Integer GetId();
    String GetLine();
    String GetDestination();
    Date GetDate();
    double GetMinDuration();
    double GetMinPrice();
    double GetTotalAmount();
}
