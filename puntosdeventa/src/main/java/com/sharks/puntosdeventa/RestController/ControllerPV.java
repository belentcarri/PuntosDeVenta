package com.sharks.puntosdeventa.RestController;

import com.sharks.puntosdeventa.Models.PuntosDeVenta;
import com.sharks.puntosdeventa.Services.ServicePV;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/puntosdeventa")
public class ControllerPV {

    private final ServicePV servicePV;

    public ControllerPV(ServicePV servicePV) {
        this.servicePV = servicePV;
    }


    @GetMapping
    public Map<Integer, PuntosDeVenta> getPuntosDeVenta() {
        return servicePV.traerPV();
    }


    @PostMapping ("/add")
    public PuntosDeVenta agregarPuntoDeVenta(@RequestBody PuntosDeVenta puntoDeVenta) {
        PuntosDeVenta nuevoPunto = servicePV.ingresarPV(puntoDeVenta);
        if (nuevoPunto != null) {
            return nuevoPunto;
        } else {
            throw new RuntimeException("Ya existe un punto de venta con ese ID");
        }
    }


    @PutMapping ("/{id}")
    public String actualizarPuntoDeVenta(@RequestBody PuntosDeVenta puntoDeVenta) {
        boolean actualizado = servicePV.actualizarPV(puntoDeVenta);
        if (actualizado) {
            return "Punto de venta actualizado";
        } else {
            return "Id inválido";
        }
    }

    @DeleteMapping ("/{id}")
    public String borrarPuntoDeVenta(@PathVariable Integer id) {
        boolean eliminado = servicePV.borrarPV(id);
        if (eliminado) {
            return "Punto de venta eliminado";
        } else {
            return "Id inválido";
        }
    }
}


