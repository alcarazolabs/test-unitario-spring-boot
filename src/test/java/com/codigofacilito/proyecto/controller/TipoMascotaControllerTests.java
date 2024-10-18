package com.codigofacilito.proyecto.controller;

import com.codigofacilito.proyecto.model.TipoMascota;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TipoMascotaControllerTests {

    @Autowired
    TestRestTemplate restTemplate;
    // Inyecta el puerto dinámico
    @LocalServerPort
    private int port;

    @Test
    void shouldReturnAllTipoMascotasWhenListIsRequested(){

        ResponseEntity<String> response = restTemplate.getForEntity("/api/tipomascotas", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int tipoMascotasCount = documentContext.read("$.length()");
        assertThat(tipoMascotasCount).isEqualTo(3);

        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactlyInAnyOrder(1, 2, 3);

        JSONArray nombres = documentContext.read("$..nombre");
        assertThat(nombres).containsExactlyInAnyOrder(
                "Perro", "Gato", "Ave");

    }


    @Test
    @DirtiesContext
    void shouldCreateANewTipoMascota() {
        TipoMascota tipoMascota = new TipoMascota("Perro");

        String url = "http://localhost:" + port + "/api/tipomascotas";

        ResponseEntity<Void> response = restTemplate.postForEntity(url, tipoMascota, Void.class);
        // Verificar si se creó el registro
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        // Obtener la URI del nuevo recurso
        URI location = response.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(location, String.class);
        // Verificar si la respuesta es 200 OK
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        // Verificar el contenido del JSON de respuesta
        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        String nombre = documentContext.read("$.nombre");
        // Afirmaciones finales
        assertThat(id).isNotNull();
        assertThat(nombre).isEqualTo("Perro");

    }




}
