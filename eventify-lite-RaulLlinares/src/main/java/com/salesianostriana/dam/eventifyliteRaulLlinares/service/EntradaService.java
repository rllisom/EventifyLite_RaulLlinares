package com.salesianostriana.dam.eventifyliteRaulLlinares.service;

import com.salesianostriana.dam.eventifyliteRaulLlinares.dto.CreateEntradaRequest;
import com.salesianostriana.dam.eventifyliteRaulLlinares.model.Asistente;
import com.salesianostriana.dam.eventifyliteRaulLlinares.model.Entrada;
import com.salesianostriana.dam.eventifyliteRaulLlinares.model.Estado;
import com.salesianostriana.dam.eventifyliteRaulLlinares.model.Evento;
import com.salesianostriana.dam.eventifyliteRaulLlinares.repository.AsistenteRepository;
import com.salesianostriana.dam.eventifyliteRaulLlinares.repository.EntradaRepository;
import com.salesianostriana.dam.eventifyliteRaulLlinares.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class EntradaService {

    private final EntradaRepository entradaRepository;
    private final EventoRepository eventoRepository;
    private final AsistenteRepository asistenteRepository;

    public Entrada comprarEntrada(CreateEntradaRequest entradaRequest){
        Evento evento = eventoRepository.findById(entradaRequest.eventoId()).orElseThrow(
            () -> new EntityNotFoundException("No existe el evento con id %d".formatted(entradaRequest.eventoId()))
        );
        Asistente asistente = asistenteRepository.findById(entradaRequest.asistenteId()).orElseThrow(
                () -> new EntityNotFoundException("No existe el asistente con id %d".formatted(entradaRequest.asistenteId()))
        );

        if(evento.getEntradasVendidas()> evento.getAforoMaximo())
            throw new IllegalArgumentException("El aforo está completo");

        Entrada entrada = Entrada.builder()
                .fechaCompra(LocalDateTime.now())
                .estado(Estado.ACTIVA)
                .evento(evento)
                .asistente(asistente)
                .build();
        evento.setEntradasVendidas(evento.getEntradasVendidas()+1);
        evento.addEntrada(entrada);
        asistente.addEntrada(entrada);
        return entradaRepository.save(entrada);
    }

    public Entrada cancelarEntrada(Long entradaId) {
        Entrada entrada = entradaRepository.findById(entradaId).orElseThrow(
                () -> new EntityNotFoundException("Entrada con id %d no encontrada".formatted(entradaId))
        );

        Evento evento = entrada.getEvento();

        entrada.setEstado(Estado.CANCELADA);
        evento.setEntradasVendidas(evento.getEntradasVendidas() - 1);

        return entradaRepository.save(entrada);
    }

    public Page<Entrada> mostrarInfoEntradasDeEvento(Long eventoId, Pageable pageable){
        return entradaRepository.findByEvento_Id(eventoId,pageable);
    }
}
