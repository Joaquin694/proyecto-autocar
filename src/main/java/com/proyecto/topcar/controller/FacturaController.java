/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.topcar.controller;

import com.proyecto.topcar.model.Factura;
import com.proyecto.topcar.repository.AutoRepository;
import com.proyecto.topcar.repository.FacturaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private AutoRepository autoRepository;

    // Mostrar todas las facturas
    @GetMapping
    public String getAllFacturas(Model model) {
        List<Factura> facturas = facturaRepository.findAll();
        model.addAttribute("facturas", facturas);
        return "facturas/index";
    }

    // Mostrar formulario para crear una nueva factura
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("factura", new Factura());
        model.addAttribute("autos", autoRepository.findAll()); // Autos disponibles
        return "facturas/create";
    }

    // Guardar una nueva factura
    @PostMapping
    public String createFactura(@ModelAttribute Factura factura) {
        facturaRepository.save(factura);
        return "redirect:/facturas";
    }

    // Mostrar detalles de una factura
    @GetMapping("/{id}")
    public String showFacturaDetails(@PathVariable Long id, Model model) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        model.addAttribute("factura", factura);
        return "facturas/details";
    }

    // Mostrar formulario para editar una factura
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        model.addAttribute("factura", factura);
        model.addAttribute("autos", autoRepository.findAll()); // Autos disponibles
        return "facturas/edit";
    }

    // Actualizar una factura
    @PostMapping("/{id}")
    public String updateFactura(@PathVariable Long id, @ModelAttribute Factura facturaDetails) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        factura.setFecha(facturaDetails.getFecha());
        factura.setTotal(facturaDetails.getTotal());
        factura.setMetodoPago(facturaDetails.getMetodoPago());
        factura.setEstadoPago(facturaDetails.getEstadoPago());
        factura.setAuto(facturaDetails.getAuto());

        facturaRepository.save(factura);
        return "redirect:/facturas";
    }

    // Eliminar una factura
    @GetMapping("/{id}/delete")
    public String deleteFactura(@PathVariable Long id) {
        facturaRepository.deleteById(id);
        return "redirect:/facturas";
    }
}