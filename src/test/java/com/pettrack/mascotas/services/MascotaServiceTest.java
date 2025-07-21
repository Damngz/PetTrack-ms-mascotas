package com.pettrack.mascotas.services;

import com.pettrack.mascotas.models.Mascota;
import com.pettrack.mascotas.repositories.MascotaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class MascotaServiceTest {
    @Mock
    private MascotaRepository mascotaRepository;

    @InjectMocks
    private MascotaService mascotaService;

    private Mascota mascota1;
    private Mascota mascota2;

    @BeforeEach
    void setUp() {
        mascota1 = new Mascota();
        mascota1.setId(1L);
        mascota1.setNombre("Firulais");
        mascota1.setEspecie("Perro");
        mascota1.setRaza("Labrador");
        mascota1.setSexo("Macho");
        mascota1.setFechaNacimiento(LocalDate.of(2020, 5, 15));
        mascota1.setIdUsuario(100L);

        mascota2 = new Mascota();
        mascota2.setId(2L);
        mascota2.setNombre("Michi");
        mascota2.setEspecie("Gato");
        mascota2.setRaza("Siamés");
        mascota2.setSexo("Hembra");
        mascota2.setFechaNacimiento(LocalDate.of(2021, 3, 10));
        mascota2.setIdUsuario(100L);
    }

    @Test
    void findAll_deberiaRetornarTodasLasMascotas() {
        when(mascotaRepository.findAll()).thenReturn(Arrays.asList(mascota1, mascota2));

        List<Mascota> resultado = mascotaService.findAll();

        assertEquals(2, resultado.size());
        verify(mascotaRepository, times(1)).findAll();
    }

    @Test
    void findById_deberiaRetornarMascotaCuandoExiste() {
        when(mascotaRepository.findById(1L)).thenReturn(Optional.of(mascota1));

        Mascota resultado = mascotaService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Firulais", resultado.getNombre());
    }

    @Test
    void findById_deberiaRetornarNullCuandoNoExiste() {
        when(mascotaRepository.findById(99L)).thenReturn(Optional.empty());

        Mascota resultado = mascotaService.findById(99L);

        assertNull(resultado);
    }

    @Test
    void save_deberiaGuardarMascota() {
        when(mascotaRepository.save(any(Mascota.class))).thenReturn(mascota1);

        Mascota nuevaMascota = new Mascota();
        nuevaMascota.setNombre("Firulais");
        // ... otros campos

        Mascota resultado = mascotaService.save(nuevaMascota);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(mascotaRepository, times(1)).save(nuevaMascota);
    }

    @Test
    void deleteById_deberiaEliminarMascota() {
        doNothing().when(mascotaRepository).deleteById(1L);

        mascotaService.deleteById(1L);

        verify(mascotaRepository, times(1)).deleteById(1L);
    }

    @Test
    void findByUsuarioId_deberiaRetornarMascotasDelUsuario() {
        when(mascotaRepository.findByIdUsuario(100L)).thenReturn(Arrays.asList(mascota1, mascota2));

        List<Mascota> resultado = mascotaService.findByUsuarioId(100L);

        assertEquals(2, resultado.size());
        assertEquals(100L, resultado.get(0).getIdUsuario());
        assertEquals(100L, resultado.get(1).getIdUsuario());
    }
}
