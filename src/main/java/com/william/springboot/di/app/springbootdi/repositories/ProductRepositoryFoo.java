package com.william.springboot.di.app.springbootdi.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.william.springboot.di.app.springbootdi.models.Product;

@Repository("productFoo")
public class ProductRepositoryFoo implements ProductRepository
{

    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L, "Monitor Asus", 600L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(id, "Monitor Asus", 600L);
    }

}
