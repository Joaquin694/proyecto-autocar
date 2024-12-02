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
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private LocalDate fechaPago;

    @Column(nullable = false, length = 50)
    private String metodoPago; // Por ejemplo, "Tarjeta", "Efectivo", etc.

    @ManyToOne
    @JoinColumn(name = "factura_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pago_factura"))
    private Factura factura;
}