/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.topcar.controller;

import com.proyecto.topcar.model.Auto;
import com.proyecto.topcar.model.Pago;
import com.proyecto.topcar.repository.AutoRepository;
import com.proyecto.topcar.repository.PagoRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private AutoRepository autoRepository;

    @GetMapping
    public String getDashboard(Model model) {
        // Obtener todos los pagos
        List<Pago> pagos = pagoRepository.findAll();
        
        // Obtener todos los autos
        List<Auto> autos = autoRepository.findAll();

        // Crear un mapa para contar la cantidad de pagos por método
        Map<String, Long> pagosPorMetodo = new HashMap<>();
        Map<LocalDate, Double> pagosPorFecha = new HashMap<>();

        // Contar pagos por método y sumar pagos por fecha
        for (Pago pago : pagos) {
            // Contar pagos por método
            pagosPorMetodo.put(pago.getMetodoPago(), pagosPorMetodo.getOrDefault(pago.getMetodoPago(), 0L) + 1);

            // Sumar pagos por fecha
            pagosPorFecha.put(pago.getFechaPago(), pagosPorFecha.getOrDefault(pago.getFechaPago(), 0.0) + pago.getMonto());
        }

        // Preparar los datos para los gráficos de pagos
        List<String> metodosPago = new ArrayList<>(pagosPorMetodo.keySet());
        List<Long> cantidadesMetodo = new ArrayList<>(pagosPorMetodo.values());

        List<LocalDate> fechasPago = new ArrayList<>(pagosPorFecha.keySet());
        List<Double> montosFecha = new ArrayList<>(pagosPorFecha.values());

        // Crear un mapa para contar autos por marca
        Map<String, Long> autosPorMarca = new HashMap<>();

        // Contar autos por marca
        for (Auto auto : autos) {
            autosPorMarca.put(auto.getMarca(), autosPorMarca.getOrDefault(auto.getMarca(), 0L) + 1);
        }

        // Preparar los datos para el gráfico de marcas de autos
        List<String> marcas = new ArrayList<>(autosPorMarca.keySet());
        List<Long> cantidadesAutos = new ArrayList<>(autosPorMarca.values());

        // Pasar los datos al modelo
        model.addAttribute("metodosPago", metodosPago);
        model.addAttribute("cantidadesMetodo", cantidadesMetodo);
        model.addAttribute("fechasPago", fechasPago);
        model.addAttribute("montosFecha", montosFecha);
        model.addAttribute("marcas", marcas);
        model.addAttribute("cantidadesAutos", cantidadesAutos);

        return "dashboard";  // Vista del dashboard
    }
}
