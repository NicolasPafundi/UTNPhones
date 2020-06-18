package com.utn.TPFinal.model.projections;

public interface MobileReportUserCallsRank {
    String getDestination();
    Integer getCallAmount();

    void setDestination(String destination);
    void setCallAmount(Integer CallAmount);
}
