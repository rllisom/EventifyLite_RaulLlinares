package com.salesianostriana.dam.eventifyliteRaulLlinares.dto;

import com.salesianostriana.dam.eventifyliteRaulLlinares.model.Entrada;
import com.salesianostriana.dam.eventifyliteRaulLlinares.model.Estado;

import java.time.LocalDateTime;

public record EntradaDto(
        Long id,
        LocalDateTime fechaCompra,
        Estado estado,
        EventoSimpleDTO eventoDTO,
        AsistenteSimpleDTO asistenteSimple
) {
    public record  EventoSimpleDTO(
            Long id,
            String titulo
    ){}
    public record AsistenteSimpleDTO(
            Long id,
            String nombre
    ){}

    public static EntradaDto of(Entrada entrada){
        return new EntradaDto(
                entrada.getId(),
                entrada.getFechaCompra(),
                entrada.getEstado(),
                new EventoSimpleDTO(entrada.getEvento().getId(), entrada.getEvento().getTitulo()),
                new AsistenteSimpleDTO(entrada.getAsistente().getId(), entrada.getAsistente().getNombre())
        );
    }
}
