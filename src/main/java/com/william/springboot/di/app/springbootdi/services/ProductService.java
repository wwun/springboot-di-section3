package com.william.springboot.di.app.springbootdi.services;

import java.util.List;
import java.util.stream.Collectors;

import com.william.springboot.di.app.springbootdi.models.Product;
import com.william.springboot.di.app.springbootdi.repositories.ProductRepository;

public class ProductService { // tiene los mismos métodos que Repository pero podrá manipular, hacer cálculos,
                              // consultas, insert, interactuar con otros repositorios

    // no es recomendable usar el repository en el controlador directamente porque
    // es donde se persisten los datos sino que se debe usar un service

    private ProductRepository repository = new ProductRepository();

    public List<Product> findAll() {
        return repository.findAll().stream().map(p -> {
            Double priceTemp = p.getPrice() * 1.25d;

            // Product newProd = new Product(p.getId(), p.getName(), priceTemp.longValue());
            // principio de inmutabilidad, se está creando un nuevo objeto para que este no
            // se modifique v2

            // p.setPrice(priceTemp.longValue()); //v1
            // return p; //v1
            Product newProd = (Product) p.clone(); // v3 clone() devuelve la nueva instancia
            newProd.setPrice(priceTemp.longValue());
            return newProd;
        }).collect(Collectors.toList());
    }

    public Product findById(Long id) {
        return repository.findById(id);
    }
}
