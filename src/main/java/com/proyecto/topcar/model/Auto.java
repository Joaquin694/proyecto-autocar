package com.proyecto.topcar.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String modelo;
    private String color;
    private String placa;
    private String marca;
    private LocalDate fechaIngreso;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'activo'")
    private String estado;
}