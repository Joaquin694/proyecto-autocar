/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.topcar.controller;

import com.proyecto.topcar.model.Pago;
import com.proyecto.topcar.repository.FacturaRepository;
import com.proyecto.topcar.repository.PagoRepository;
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
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    // Mostrar todos los pagos
    @GetMapping
    public String getAllPagos(Model model) {
        List<Pago> pagos = pagoRepository.findAll();
        model.addAttribute("pagos", pagos);
        return "pagos/index";
    }

    // Mostrar formulario para crear un nuevo pago
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("pago", new Pago());
        model.addAttribute("facturas", facturaRepository.findAll()); // Facturas disponibles
        return "pagos/create";
    }

    // Guardar un nuevo pago
    @PostMapping
    public String createPago(@ModelAttribute Pago pago) {
        pagoRepository.save(pago);
        return "redirect:/pagos";
    }

    // Mostrar detalles de un pago
    @GetMapping("/{id}")
    public String showPagoDetails(@PathVariable Long id, Model model) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        model.addAttribute("pago", pago);
        return "pagos/details";
    }

    // Mostrar formulario para editar un pago
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        model.addAttribute("pago", pago);
        model.addAttribute("facturas", facturaRepository.findAll()); // Facturas disponibles
        return "pagos/edit";
    }

    // Actualizar un pago
    @PostMapping("/{id}")
    public String updatePago(@PathVariable Long id, @ModelAttribute Pago pagoDetails) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        pago.setMonto(pagoDetails.getMonto());
        pago.setFechaPago(pagoDetails.getFechaPago());
        pago.setMetodoPago(pagoDetails.getMetodoPago());
        pago.setFactura(pagoDetails.getFactura());

        pagoRepository.save(pago);
        return "redirect:/pagos";
    }

    // Eliminar un pago
    @GetMapping("/{id}/delete")
    public String deletePago(@PathVariable Long id) {
        pagoRepository.deleteById(id);
        return "redirect:/pagos";
    }
}