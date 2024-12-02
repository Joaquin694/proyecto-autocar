/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.topcar.controller;

import com.proyecto.topcar.model.Tiketera;
import com.proyecto.topcar.repository.AutoRepository;
import com.proyecto.topcar.repository.TiketeraRepository;
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
@RequestMapping("/tiketeras")
public class TiketeraController {

    @Autowired
    private TiketeraRepository tiketeraRepository;

    @Autowired
    private AutoRepository autoRepository;

    // Mostrar todos los tickets
    @GetMapping
    public String getAllTiketeras(Model model) {
        List<Tiketera> tiketeras = tiketeraRepository.findAll();
        model.addAttribute("tiketeras", tiketeras);
        return "tiketeras/index";
    }

    // Formulario para crear un ticket
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("tiketera", new Tiketera());
        model.addAttribute("autos", autoRepository.findAll());
        return "tiketeras/create";
    }

    // Guardar un nuevo ticket
    @PostMapping
    public String createTiketera(@ModelAttribute Tiketera tiketera) {
        tiketeraRepository.save(tiketera);
        return "redirect:/tiketeras";
    }

    // Eliminar un ticket
    @GetMapping("/{id}/delete")
    public String deleteTiketera(@PathVariable Long id) {
        tiketeraRepository.deleteById(id);
        return "redirect:/tiketeras";
    }
}