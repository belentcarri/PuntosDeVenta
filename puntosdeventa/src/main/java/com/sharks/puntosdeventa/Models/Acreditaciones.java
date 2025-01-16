package com.sharks.puntosdeventa.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Acreditaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double importe;
    private Integer idPuntoVenta;
    private Date fechaRecepcion;
    private String nombrePuntoVenta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Integer getIdPuntoVenta() {
        return idPuntoVenta;
    }

    public void setIdPuntoVenta(Integer idPuntoVenta) {
        this.idPuntoVenta = idPuntoVenta;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getNombrePuntoVenta() {
        return nombrePuntoVenta;
    }

    public void setNombrePuntoVenta(String nombrePuntoVenta) {
        this.nombrePuntoVenta = nombrePuntoVenta;
    }


}
