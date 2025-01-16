package com.sharks.puntosdeventa.Services;


import com.sharks.puntosdeventa.Models.Acreditaciones;
import com.sharks.puntosdeventa.Models.PuntosDeVenta;
import com.sharks.puntosdeventa.Repository.RepositorioAcreditaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceAcreditaciones {

    @Autowired
    private RepositorioAcreditaciones repositorioAcreditaciones;
    @Autowired
    private ServicePV servicePV;

    public Acreditaciones recibir(Double importe, Integer idPuntoVenta) {

        Optional<PuntosDeVenta> puntoDeVentaOpt = Optional.ofNullable(servicePV.traerPV().get(idPuntoVenta));
        if (!puntoDeVentaOpt.isPresent()) {
            throw new IllegalArgumentException("El punto de venta con ID " + idPuntoVenta + " no existe.");
        }
        else {
            Acreditaciones acreditaciones = new Acreditaciones();
            acreditaciones.setImporte(importe);
            acreditaciones.setIdPuntoVenta(idPuntoVenta);
            acreditaciones.setFechaRecepcion(new Date());
            acreditaciones.setNombrePuntoVenta(puntoDeVentaOpt.get().getNombre());

            return repositorioAcreditaciones.save(acreditaciones);

        }
    }

    public List<Acreditaciones> consultarAcreditaciones() {
        return repositorioAcreditaciones.findAll();
    }
}
