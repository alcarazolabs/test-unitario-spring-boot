package com.codigofacilito.proyecto.controller;


import com.codigofacilito.proyecto.model.TipoMascota;
import com.codigofacilito.proyecto.service.TipoMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/tipomascotas")
public class TipoMascotaController {

    @Autowired
    private TipoMascotaService tipoMascotaService;

    @GetMapping()
    public ResponseEntity<List<TipoMascota>> index(){
        return tipoMascotaService.index();
    }

    @PostMapping
    public ResponseEntity<TipoMascota> store(@RequestBody TipoMascota tipoMascota, UriComponentsBuilder ucb){
        return tipoMascotaService.store(tipoMascota, ucb);
    }



}
