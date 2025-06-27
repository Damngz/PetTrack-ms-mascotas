package com.pettrack.mascotas.controllers;

import org.springframework.web.bind.annotation.*;

import com.pettrack.mascotas.models.Mascota;
import com.pettrack.mascotas.services.MascotaService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/mascotas")
public class MascotaController {
  private final MascotaService service;

  public MascotaController(MascotaService service) {
    this.service = service;
  }

  @GetMapping
  public List<Mascota> getAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Mascota getOne(@PathVariable Long id) {
    return service.findById(id);
  }

  @PostMapping
  public Mascota create(@RequestBody Mascota mascota) {
    return service.save(mascota);
  }

  @PutMapping("/{id}")
  public Mascota update(@PathVariable Long id, @RequestBody Mascota mascota) {
    mascota.setId(id);
    return service.save(mascota);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    service.deleteById(id);
  }

  @GetMapping("/usuario/{usuarioId}")
  public List<Mascota> getByUsuario(@PathVariable Long usuarioId) {
    return service.findByUsuarioId(usuarioId);
  }
}
