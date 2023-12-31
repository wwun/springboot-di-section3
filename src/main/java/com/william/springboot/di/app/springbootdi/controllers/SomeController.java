package com.william.springboot.di.app.springbootdi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.william.springboot.di.app.springbootdi.models.Product;
import com.william.springboot.di.app.springbootdi.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class SomeController {

    // private ProductServiceImpl service = new ProductServiceImpl(); // con
    // Autowired

    @Autowired  //se deja con Autowired para tener ambas versiones pero con constructor es mejor
    private ProductService service; // la idea con inyección de dependencia es ya no inyectar mediante la clase sino
                                    // mediante la interface
                                    // Springboot busca la interface y luego la clase que la implementa

    /*public SomeController(ProductService service) {
        this.service = service;
    }*/

    @GetMapping
    public List<Product> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Product show(@PathVariable Long id) {
        return service.findById(id);
    }
}
