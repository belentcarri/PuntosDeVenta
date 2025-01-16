package com.sharks.puntosdeventa.RestController;

import com.sharks.puntosdeventa.Models.CostosPV;
import com.sharks.puntosdeventa.Models.PuntosDeVenta;
import com.sharks.puntosdeventa.Services.ServicePV;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/costos")
public class ControllerCostos {
    private final ServicePV servicePV;
    public ControllerCostos(ServicePV servicePV){
        this. servicePV =servicePV;
    }

    @GetMapping("/pvdirectos/{idA}")
    public ResponseEntity<Map<String, Integer>> consultarCostosDirectos(@PathVariable Integer idA) {
        // Obtenemos todos los costos y buscamos aquellos que contienen el punto A
        Map<String, Integer> costos = servicePV.traerCostos();
        Map<String, Integer> pvYcostosDirectos = new HashMap<>();

        Map<Integer, PuntosDeVenta> puntosDeVenta = servicePV.traerPV();

        for (String key : costos.keySet()) {
            String[] ids = key.split("-");
            Integer idB = Integer.valueOf(ids[1]);

            if (Integer.valueOf(ids[0]).equals(idA)) {
                String pvS = puntosDeVenta.get(idB).getNombre();
               pvYcostosDirectos.put(pvS, costos.get(key));
            }
        }
        return ResponseEntity.ok(pvYcostosDirectos);
    }

    @PostMapping("/add")
    public ResponseEntity<String> agregarC(@RequestBody CostosPV costosPV){
        try{
            servicePV.agregarOActualizarCosto(costosPV);
            return ResponseEntity.ok("Costo agregado");
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    @DeleteMapping("/eliminar/{idA}/{idB}")
    public ResponseEntity<String> eliminarC(@PathVariable Integer idA, @PathVariable Integer idB) {
        boolean borrado = servicePV.borrarCosto(idA, idB);
        if (borrado){
            return ResponseEntity.ok("Costo eliminado");
         }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe costo entre los puntos: "+idA+" y "+idB);
        }

    }

    @GetMapping("/camino/{idA}/{idB}")
    public ResponseEntity<Map<String, Object>> pvCaminoMinimo(@PathVariable Integer idA, @PathVariable Integer idB) {
        // Traemos todos los costos entre puntos de venta
        Map<String, Integer> costos = servicePV.traerCostos();

        // Creamos un mapa para almacenar el costo mínimo y el camino
        Map<String, Object> respuesta = new HashMap<>();
        Integer costoMinimo = Integer.MAX_VALUE; // Comenzamos con un valor muy alto para el costo mínimo
        String camino = ""; // Inicializamos el camino vacío

        // Comenzamos a revisar todos los caminos entre los puntos
        for (String key : costos.keySet()) {
            String[] ids = key.split("-");  // Extraemos los dos puntos de cada camino
            Integer idInicio = Integer.valueOf(ids[0]);
            Integer idFin = Integer.valueOf(ids[1]);

            // Si el camino comienza en A, y termina en B
            if (idInicio.equals(idA) && idFin.equals(idB)) {
                // Comparamos el costo actual con el mínimo encontrado hasta ahora
                Integer costoActual = costos.get(key);
                if (costoActual < costoMinimo) {
                    costoMinimo = costoActual; // Actualizamos el costo mínimo
                    camino = servicePV.traerPV().get(idA).getNombre() + " - " + servicePV.traerPV().get(idB).getNombre(); // Guardamos el camino
                }
            }
        }

        if (!camino.isEmpty()) {
            respuesta.put("costo", costoMinimo);
            respuesta.put("camino", camino);
        } else {
            // Si no hay camino, devolvemos un mensaje de error
            respuesta.put("mensaje", "No se encontró un camino entre A y B");
        }

        return ResponseEntity.ok(respuesta);
    }





}
