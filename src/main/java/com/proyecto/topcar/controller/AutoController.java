/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.topcar.controller;


import com.proyecto.topcar.model.Auto;
import com.proyecto.topcar.repository.AutoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/autos")
public class AutoController {

    @Autowired
    private AutoRepository autoRepository;

    // Mostrar todos los autos
    @GetMapping
    public String getAllAutos(Model model) {
        List<Auto> autos = autoRepository.findAll();
        model.addAttribute("autos", autos);
        return "autos/index"; // Renderiza la vista autos/index.html
    }

    // Mostrar formulario para crear un nuevo auto
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("auto", new Auto()); // Modelo vacío para el formulario
        return "autos/create"; // Renderiza la vista autos/create.html
    }

    // Guardar un nuevo auto
    @PostMapping
    public String createAuto(@ModelAttribute Auto auto) {
        autoRepository.save(auto); // Guarda el auto en la base de datos
        return "redirect:/autos"; // Redirige a la lista de autos
    }

    // Mostrar detalles de un auto
    @GetMapping("/{id}")
    public String showAutoDetails(@PathVariable Long id, Model model) {
        Auto auto = autoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auto no encontrado"));
        model.addAttribute("auto", auto);
        return "autos/details"; // Renderiza la vista autos/details.html
    }

    // Mostrar formulario para editar un auto
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Auto auto = autoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auto no encontrado"));
        model.addAttribute("auto", auto); // Pasa el auto existente al modelo
        return "autos/edit"; // Renderiza la vista autos/edit.html
    }

    // Actualizar un auto existente
    @PostMapping("/{id}")
    public String updateAuto(@PathVariable Long id, @ModelAttribute Auto autoDetails) {
        Auto auto = autoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auto no encontrado"));

        // Actualiza los campos del auto
        auto.setNombre(autoDetails.getNombre());
        auto.setModelo(autoDetails.getModelo());
        auto.setColor(autoDetails.getColor());
        auto.setPlaca(autoDetails.getPlaca());
        auto.setMarca(autoDetails.getMarca());
        auto.setFechaIngreso(autoDetails.getFechaIngreso());
        auto.setEstado(autoDetails.getEstado());

        autoRepository.save(auto); // Guarda los cambios en la base de datos
        return "redirect:/autos"; // Redirige a la lista de autos
    }

    // Eliminar un auto
    @GetMapping("/{id}/delete")
    public String deleteAuto(@PathVariable Long id) {
        autoRepository.deleteById(id); // Elimina el auto por su ID
        return "redirect:/autos"; // Redirige a la lista de autos
    }
}