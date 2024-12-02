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
public class Tiketera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "auto_id", nullable = false, foreignKey = @ForeignKey(name = "fk_tiketera_auto"))
    private Auto auto;

    @Column(nullable = false, length = 50, unique = true)
    private String ticket;

    @Column(nullable = false)
    private LocalDate fechaEmision;

    @Column(nullable = false, length = 50)
    private String estadoTicket = "activo"; // Valor predeterminado asignado en el modelo.
}