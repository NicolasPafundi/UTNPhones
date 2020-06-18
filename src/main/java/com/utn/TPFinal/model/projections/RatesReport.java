package com.utn.TPFinal.model.projections;

public interface RatesReport {
    String getOrigen();
    String getDestino();
    double getPrecio();

    void setOrigen(String orgine);
    void setDestino(String destino);
    void setPrecio(double precio);
}
