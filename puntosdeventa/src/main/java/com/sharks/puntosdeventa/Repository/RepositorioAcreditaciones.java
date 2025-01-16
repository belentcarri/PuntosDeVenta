package com.sharks.puntosdeventa.Repository;

import com.sharks.puntosdeventa.Models.Acreditaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioAcreditaciones extends JpaRepository<Acreditaciones, Long> {
}
