package com.william.springboot.di.app.springbootdi.repositories;

import java.util.Arrays;
import java.util.List;

import com.william.springboot.di.app.springbootdi.models.Product;

public class ProductRepository { // capa de datos
    private List<Product> data;

    public ProductRepository() {
        this.data = Arrays.asList(new Product(1L, "Memoria corsair", 300L), new Product(2L, "Cpu Intel Core", 85L),
                new Product(3L, "Teclado Razer", 180L), new Product(4L, "Motherboard Gigabyte", 490L));
    }

    public List<Product> findAll() {
        return data;
    }

    public Product findById(Long id) {
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

}
