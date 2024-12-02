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
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String producto;
    private Integer cantidad;
    private Double precioUnitario;
    private String proveedor;
    private LocalDate fechaEntrada;
}
