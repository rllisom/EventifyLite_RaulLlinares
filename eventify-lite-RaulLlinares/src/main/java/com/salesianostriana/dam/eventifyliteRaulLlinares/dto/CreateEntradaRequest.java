package com.salesianostriana.dam.eventifyliteRaulLlinares.dto;

import java.time.LocalDateTime;

public record CreateEntradaRequest(
        Long eventoId,
        Long asistenteId
) {
}
