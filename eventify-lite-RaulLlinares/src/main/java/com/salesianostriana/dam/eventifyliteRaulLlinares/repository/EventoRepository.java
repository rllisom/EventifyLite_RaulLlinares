package com.salesianostriana.dam.eventifyliteRaulLlinares.repository;

import com.salesianostriana.dam.eventifyliteRaulLlinares.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {
}
