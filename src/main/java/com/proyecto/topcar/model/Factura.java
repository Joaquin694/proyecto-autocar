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
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false, length = 50)
    private String metodoPago; // Por ejemplo, "Tarjeta", "Efectivo", etc.

    @Column(nullable = false, length = 50)
    private String estadoPago = "pendiente"; // Valor predeterminado en el modelo.

    @ManyToOne
    @JoinColumn(name = "auto_id", nullable = false, foreignKey = @ForeignKey(name = "fk_factura_auto"))
    private Auto auto;
}