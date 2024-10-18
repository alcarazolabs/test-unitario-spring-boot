package com.codigofacilito.proyecto.init;


import com.codigofacilito.proyecto.model.TipoMascota;
import com.codigofacilito.proyecto.repository.TipoMascotaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;


@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TipoMascotaRepository tipoMascotaRepository) {
        return args -> {
            if (tipoMascotaRepository.count() == 0) {

                log.info("========================== REGISTRANDO DATA EN LA BD ==========================");

                TipoMascota perro = new TipoMascota("Perro");
                TipoMascota gato = new TipoMascota("Gato");
                TipoMascota ave = new TipoMascota("Ave");

                 tipoMascotaRepository.save(perro);
                 tipoMascotaRepository.save(gato);
                tipoMascotaRepository.save(ave);

                log.info("Tipos de Mascotas Registrados...");


                log.info("========================== FINALIZO EL REGISTRO DE DATA EN LA BD ==========================");
            }


        };
    }

}
