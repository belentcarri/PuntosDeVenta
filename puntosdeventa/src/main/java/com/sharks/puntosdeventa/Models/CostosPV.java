package com.sharks.puntosdeventa.Models;

public class CostosPV {

    public Integer idA;
    public Integer idB;
    public Integer costo;

    public CostosPV(Integer idA, Integer idB, Integer costo){
        this.idA = idA;
        this.idB = idB;
        this.costo = costo;
    }

    public Integer getIdA() {
        return idA;
    }

    public void setIdA(Integer idA) {
        this.idA = idA;
    }

    public Integer getIdB() {
        return idB;
    }

    public void setIdB(Integer idB) {
        this.idB = idB;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }
}
