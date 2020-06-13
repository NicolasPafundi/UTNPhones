package com.utn.TPFinal.model.projections;

import java.util.Date;

public interface ReportCallsByUserByDate {
    String getNumeroOrigen();
    String getCiudadOrigen();
    String getNumeroDestino();
    String getCiudadDestino();
    double getPrecioTotal();
    double getDuracion();
    Date getFechaLlamada();

    void setNumeroOrigen(String numeroOrigen);
    void setCiudadOrigen(String ciudadOrigen);
    void setNumeroDestino(String numeroDestino);
    void setCiudadDestino(String ciudadDestino);
    void setTotalAmount(double totalAmount);
    void setMinDuration(double minDuration);
    void setFechaLlamada(Date fechaLlamada);
}
