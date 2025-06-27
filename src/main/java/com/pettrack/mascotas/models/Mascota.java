package com.pettrack.mascotas.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "mascota")
public class Mascota {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_mascota")
  private Long id;

  @Column
  private String nombre;

  @Column
  private String especie;

  @Column
  private String raza;

  @Column
  private String sexo;

  @Column(name = "fecha_nacimiento")
  private LocalDate fechaNacimiento;

  @Column(name = "id_usuario")
  private Long idUsuario;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEspecie() {
    return especie;
  }

  public void setEspecie(String especie) {
    this.especie = especie;
  }

  public String getRaza() {
    return raza;
  }

  public void setRaza(String raza) {
    this.raza = raza;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public Long getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Long idUsuario) {
    this.idUsuario = idUsuario;
  }
}
