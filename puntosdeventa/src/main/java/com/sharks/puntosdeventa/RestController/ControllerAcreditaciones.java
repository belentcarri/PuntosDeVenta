package com.sharks.puntosdeventa.RestController;

import com.sharks.puntosdeventa.Models.Acreditaciones;
import com.sharks.puntosdeventa.Models.AcreditacionesPost;
import com.sharks.puntosdeventa.Services.ServiceAcreditaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acreditaciones")
public class ControllerAcreditaciones {
    @Autowired
    private ServiceAcreditaciones serviceAcreditaciones;

    @PostMapping("/add")
    public ResponseEntity<String> agregarA(@RequestBody AcreditacionesPost acreditacionesPost){
        try {
            serviceAcreditaciones.recibir(acreditacionesPost.getImporte(), acreditacionesPost.getIdPuntoVenta());
            return ResponseEntity.ok("Acreditación exitosa");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(400).body(e.getMessage());

        }
    }



    @GetMapping("/todasAcreditaciones")
    public ResponseEntity<List<Acreditaciones>> traerA(){
        List<com.sharks.puntosdeventa.Models.Acreditaciones> acreditaciones = serviceAcreditaciones.consultarAcreditaciones();
        return ResponseEntity.ok(acreditaciones);

    }

}
