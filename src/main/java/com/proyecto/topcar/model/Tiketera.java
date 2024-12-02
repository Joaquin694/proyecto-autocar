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
    @JoinColumn(name = "auto_id", nullable = false)
    private Auto auto;

    private String ticket;
    private LocalDate fechaEmision;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'activo'")
    private String estadoTicket;
}
