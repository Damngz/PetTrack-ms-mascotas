package com.pettrack.mascotas.services;

import org.springframework.stereotype.Service;

import com.pettrack.mascotas.models.Mascota;
import com.pettrack.mascotas.repositories.MascotaRepository;

import java.util.List;

@Service
public class MascotaService {
  private final MascotaRepository repo;

  public MascotaService(MascotaRepository repo) {
    this.repo = repo;
  }

  public List<Mascota> findAll() {
    return repo.findAll();
  }

  public Mascota findById(Long id) {
    return repo.findById(id).orElse(null);
  }

  public Mascota save(Mascota mascota) {
    return repo.save(mascota);
  }

  public void deleteById(Long id) {
    repo.deleteById(id);
  }

  public List<Mascota> findByUsuarioId(Long usuarioId) {
    return repo.findByIdUsuario(usuarioId);
  }

}
