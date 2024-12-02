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
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private Double total;

    private String metodoPago;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'pendiente'")
    private String estadoPago;

    @ManyToOne
    @JoinColumn(name = "auto_id", nullable = false)
    private Auto auto;
}
