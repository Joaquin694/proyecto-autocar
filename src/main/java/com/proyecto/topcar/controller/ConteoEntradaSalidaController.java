/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.topcar.controller;

import com.proyecto.topcar.model.ConteoEntradaSalida;
import com.proyecto.topcar.repository.AutoRepository;
import com.proyecto.topcar.repository.ConteoEntradaSalidaRepository;
import com.proyecto.topcar.repository.InventarioRepository;
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
@RequestMapping("/conteos")
public class ConteoEntradaSalidaController {

    @Autowired
    private ConteoEntradaSalidaRepository conteoRepository;

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    // Mostrar todos los conteos
    @GetMapping
    public String getAllConteos(Model model) {
        List<ConteoEntradaSalida> conteos = conteoRepository.findAll();
        model.addAttribute("conteos", conteos);
        return "conteos/index";
    }

    // Formulario para crear un conteo
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("conteo", new ConteoEntradaSalida());
        model.addAttribute("autos", autoRepository.findAll());
        model.addAttribute("productos", inventarioRepository.findAll());
        return "conteos/create";
    }

    // Guardar un nuevo conteo
    @PostMapping
    public String createConteo(@ModelAttribute ConteoEntradaSalida conteo) {
        conteoRepository.save(conteo);
        return "redirect:/conteos";
    }

    // Mostrar formulario para editar un conteo
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ConteoEntradaSalida conteo = conteoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteo no encontrado"));
        model.addAttribute("conteo", conteo);
        model.addAttribute("autos", autoRepository.findAll());
        model.addAttribute("productos", inventarioRepository.findAll());
        return "conteos/edit";
    }

    // Actualizar un conteo
    @PostMapping("/{id}")
    public String updateConteo(@PathVariable Long id, @ModelAttribute ConteoEntradaSalida conteoDetails) {
        ConteoEntradaSalida conteo = conteoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteo no encontrado"));

        conteo.setTipo(conteoDetails.getTipo());
        conteo.setCantidad(conteoDetails.getCantidad());
        conteo.setFecha(conteoDetails.getFecha());
        conteo.setAuto(conteoDetails.getAuto());
        conteo.setProducto(conteoDetails.getProducto());

        conteoRepository.save(conteo);
        return "redirect:/conteos";
    }

    // Eliminar un conteo
    @GetMapping("/{id}/delete")
    public String deleteConteo(@PathVariable Long id) {
        conteoRepository.deleteById(id);
        return "redirect:/conteos";
    }
}