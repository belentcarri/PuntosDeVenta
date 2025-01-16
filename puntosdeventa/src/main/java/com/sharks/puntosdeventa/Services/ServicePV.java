package com.sharks.puntosdeventa.Services;

import com.sharks.puntosdeventa.Models.*;

import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class ServicePV {

    private HashMap<Integer, PuntosDeVenta> cachePV = new HashMap<>();
    private HashMap<String, Integer> cacheCostos = new HashMap<>();


    public ServicePV(){
        cachePV.put (1, new PuntosDeVenta(1,"CABA"));
        cachePV.put (2, new PuntosDeVenta(2,"GBA_1"));
        cachePV.put (3, new PuntosDeVenta(3,"GBA_2"));
        cachePV.put (4, new PuntosDeVenta(4,"Santa Fe"));
        cachePV.put (5, new PuntosDeVenta(5,"Cordoba"));
        cachePV.put (6, new PuntosDeVenta(6,"Misiones"));
        cachePV.put (7, new PuntosDeVenta(7,"Salta"));
        cachePV.put (8, new PuntosDeVenta(8,"Chubut"));
        cachePV.put (9, new PuntosDeVenta(9,"Santa Cruz"));
        cachePV.put (10, new PuntosDeVenta(10,"Catamarca"));


        //2.Costos:
        cacheCostos.put("1-2",2);
        cacheCostos.put("1-3",3);
        cacheCostos.put("2-3",5);
        cacheCostos.put("2-4",10);
        cacheCostos.put("1-4",11);
        cacheCostos.put("4-5",5);
        cacheCostos.put("2-5",14);
        cacheCostos.put("6-7",32);
        cacheCostos.put("8-9",11);
        cacheCostos.put("10-7",5);
        cacheCostos.put("3-8",10);
        cacheCostos.put("5-8",30);
        cacheCostos.put("10-5",5);
        cacheCostos.put("4-6",6);

    }


    public Map<Integer, PuntosDeVenta> traerPV() {
        return cachePV;
    }




    public PuntosDeVenta ingresarPV(PuntosDeVenta puntoVenta) {
        if (!cachePV.containsKey(puntoVenta.getId())) {
            cachePV.put(puntoVenta.getId(), puntoVenta);
            return puntoVenta;
        } else {
            return null;
        }
    }


    public boolean actualizarPV(PuntosDeVenta puntoVenta) {
        if (cachePV.containsKey(puntoVenta.getId())) {
            cachePV.put(puntoVenta.getId(), puntoVenta);
            return true;
        } else {
            return false;
        }
    }

    public boolean borrarPV(Integer id) {
        if (cachePV.containsKey(id)) {
            cachePV.remove(id);
            return true;
        } else {
            return false;
        }
    }

    //Traer, actualizar,agregar y borrar costos:
    public Map<String, Integer> traerCostos() {
        return cacheCostos;
    }


    public boolean agregarOActualizarCosto(CostosPV costosPV) {

        String camino = costosPV.getIdA() + "-" + costosPV.getIdB();
        String caminoReverso= costosPV.getIdB() + "-" + costosPV.getIdA();

        if (costosPV.getCosto() < 0) {
            throw new IllegalArgumentException("Costo inválido, por favor proporcione un costo mayor a 0");
        }
        else if (costosPV.getIdA().equals(costosPV.getIdB()) && costosPV.getCosto() != 0) {
            throw new IllegalArgumentException("Error: el punto de venta no puede ir a sí mismo");
        }

        else if (!cachePV.containsKey(costosPV.getIdA())) {
            throw new IllegalArgumentException("El punto de venta con ID: "+ costosPV.getIdA()+", no existe");
        }
        else if (!cachePV.containsKey(costosPV.getIdB())) {
            throw new IllegalArgumentException("El punto de venta con ID: "+ costosPV.getIdB()+", no existe");
        }
        else {
            cacheCostos.put(camino, costosPV.getCosto());
            cacheCostos.put(caminoReverso, costosPV.getCosto());
            return true;
        }
    }

    public boolean borrarCosto(Integer idA, Integer idB){

        String camino = idA +"-"+ idB;
        String caminoReverso = idB +"-"+ idA;

        if(cacheCostos.containsKey(camino)){
            cacheCostos.remove(camino);
            cacheCostos.remove(caminoReverso);
            return true;
        }
        else{
            return false;
        }

    }

}