/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.topcar.controller;

import com.proyecto.topcar.model.Inventario;
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
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioRepository inventarioRepository;

    // Mostrar todos los productos
    @GetMapping
    public String getAllProductos(Model model) {
        List<Inventario> inventario = inventarioRepository.findAll();
        model.addAttribute("inventario", inventario);
        return "inventario/index";
    }

    // Formulario para crear un producto
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("producto", new Inventario());
        return "inventario/create";
    }

    // Guardar un nuevo producto
    @PostMapping
    public String createProducto(@ModelAttribute Inventario inventario) {
        inventarioRepository.save(inventario);
        return "redirect:/inventario";
    }

    // Mostrar formulario para editar un producto
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        model.addAttribute("producto", inventario);
        return "inventario/edit";
    }

    // Actualizar un producto
    @PostMapping("/{id}")
    public String updateProducto(@PathVariable Long id, @ModelAttribute Inventario productoDetails) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        inventario.setProducto(productoDetails.getProducto());
        inventario.setCantidad(productoDetails.getCantidad());
        inventario.setPrecioUnitario(productoDetails.getPrecioUnitario());
        inventario.setProveedor(productoDetails.getProveedor());
        inventario.setFechaEntrada(productoDetails.getFechaEntrada());

        inventarioRepository.save(inventario);
        return "redirect:/inventario";
    }

    // Eliminar un producto
    @GetMapping("/{id}/delete")
    public String deleteProducto(@PathVariable Long id) {
        inventarioRepository.deleteById(id);
        return "redirect:/inventario";
    }
}