package com.codigofacilito.proyecto.service;

import com.codigofacilito.proyecto.model.TipoMascota;
import com.codigofacilito.proyecto.repository.TipoMascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class TipoMascotaService {

    @Autowired
    private TipoMascotaRepository tipoMascotaRepository;


    public ResponseEntity<List<TipoMascota>> index() {
        return ResponseEntity.ok(tipoMascotaRepository.findAll());
    }


    public ResponseEntity<TipoMascota> store(@RequestBody TipoMascota nuevoTipoMascota,
                                         UriComponentsBuilder ucb) {
        //guardar la mascota.
        TipoMascota tipoMascotaGuardado = tipoMascotaRepository.save(nuevoTipoMascota);

        URI uriTipoMascota = ucb
                .path("api/tipomascotas/{id}")
                .buildAndExpand(tipoMascotaGuardado.getId())
                .toUri();
        //return ResponseEntity.created(uriTipoMascota).build();
        return ResponseEntity.created(uriTipoMascota).body(tipoMascotaGuardado);

    }


}
