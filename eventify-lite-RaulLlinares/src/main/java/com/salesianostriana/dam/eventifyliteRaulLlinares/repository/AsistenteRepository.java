package com.salesianostriana.dam.eventifyliteRaulLlinares.repository;

import com.salesianostriana.dam.eventifyliteRaulLlinares.model.Asistente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenteRepository extends JpaRepository<Asistente,Long> {
}
