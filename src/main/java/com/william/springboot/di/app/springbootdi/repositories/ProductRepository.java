package com.william.springboot.di.app.springbootdi.repositories;

import java.util.List;

import com.william.springboot.di.app.springbootdi.models.Product;

public interface ProductRepository {
    List<Product> findAll();

    Product findById(Long id);
}
