package com.salesianostriana.dam.eventifyliteRaulLlinares.controller;

import com.salesianostriana.dam.eventifyliteRaulLlinares.dto.CreateEntradaRequest;
import com.salesianostriana.dam.eventifyliteRaulLlinares.dto.EntradaDto;
import com.salesianostriana.dam.eventifyliteRaulLlinares.service.EntradaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EntradaController {

    private final EntradaService entradaService;

    @PostMapping("/entradas")
    public ResponseEntity<EntradaDto> comprarEntrada(@RequestParam CreateEntradaRequest entradaRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EntradaDto.of(entradaService.comprarEntrada(entradaRequest)));
    }

    @PutMapping("/entradas/{id}/cancelar")
    public ResponseEntity<EntradaDto> cancelarEntrada(@PathVariable Long id){
        return ResponseEntity.ok(EntradaDto.of(entradaService.cancelarEntrada(id)));
    }

    @GetMapping("/eventos/{id}/entradas")
    public Page<EntradaDto> mostrarInfoEntradasDeEvento(@PathVariable Long eventoId,
                                                        @PageableDefault(page = 0,size = 20 )Pageable pageable){
        return entradaService.mostrarInfoEntradasDeEvento(eventoId,pageable).map(EntradaDto::of);
    }
}
