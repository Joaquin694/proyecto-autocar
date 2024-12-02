/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.topcar.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;


@Data
@Entity
public class ConteoEntradaSalida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "auto_id", nullable = false, foreignKey = @ForeignKey(name = "fk_conteo_auto"))
    private Auto auto;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false, foreignKey = @ForeignKey(name = "fk_conteo_producto"))
    private Inventario producto;

    @Column(nullable = false, length = 50)
    private String tipo; // 'Entrada' o 'Salida'

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private LocalDate fecha;
}