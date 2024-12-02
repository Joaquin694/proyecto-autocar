/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.topcar.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class ConteoEntradaSalida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "auto_id", nullable = false)
    private Auto auto;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Inventario producto;

    private String tipo; // 'Entrada' o 'Salida'
    private Integer cantidad;
    private LocalDate fecha;
}
