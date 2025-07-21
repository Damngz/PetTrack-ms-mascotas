package com.pettrack.mascotas.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pettrack.mascotas.models.Mascota;
import com.pettrack.mascotas.services.MascotaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MascotaController.class)
class MascotaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MascotaService mascotaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Mascota sampleMascota() {
        Mascota m = new Mascota();
        m.setId(1L);
        m.setNombre("Firulais");
        m.setEspecie("Perro");
        m.setRaza("Labrador");
        m.setSexo("Macho");
        m.setFechaNacimiento(LocalDate.of(2020, 1, 1));
        m.setIdUsuario(10L);
        return m;
    }

    @Test
    void debeListarTodasLasMascotas() throws Exception {
        when(mascotaService.findAll()).thenReturn(List.of(sampleMascota()));

        mockMvc.perform(get("/mascotas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Firulais"));
    }

    @Test
    void debeObtenerMascotaPorId() throws Exception {
        when(mascotaService.findById(1L)).thenReturn(sampleMascota());

        mockMvc.perform(get("/mascotas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Firulais"));
    }

    @Test
    void debeCrearMascota() throws Exception {
        Mascota mascota = sampleMascota();
        mascota.setId(null);

        Mascota guardada = sampleMascota();

        when(mascotaService.save(any(Mascota.class))).thenReturn(guardada);

        mockMvc.perform(post("/mascotas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mascota)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Firulais"));
    }

    @Test
    void debeActualizarMascota() throws Exception {
        Mascota actualizada = sampleMascota();
        actualizada.setNombre("Max");

        when(mascotaService.save(any(Mascota.class))).thenReturn(actualizada);

        mockMvc.perform(put("/mascotas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actualizada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Max"));
    }

    @Test
    void debeEliminarMascota() throws Exception {
        doNothing().when(mascotaService).deleteById(1L);

        mockMvc.perform(delete("/mascotas/1"))
                .andExpect(status().isOk());

        verify(mascotaService, times(1)).deleteById(1L);
    }

    @Test
    void debeListarMascotasPorUsuario() throws Exception {
        when(mascotaService.findByUsuarioId(10L)).thenReturn(List.of(sampleMascota()));

        mockMvc.perform(get("/mascotas/usuario/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].idUsuario").value(10));
    }
}
