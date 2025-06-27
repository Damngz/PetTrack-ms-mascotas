package com.pettrack.mascotas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pettrack.mascotas.models.Mascota;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
  List<Mascota> findByIdUsuario(Long idUsuario);
}
