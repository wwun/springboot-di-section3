package com.william.springboot.di.app.springbootdi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.william.springboot.di.app.springbootdi.models.Product;
import com.william.springboot.di.app.springbootdi.repositories.ProductRepository;
import com.william.springboot.di.app.springbootdi.repositories.ProductService;

@Service // indica que es un componente de java
public class ProductServiceImpl implements ProductService { // tiene los mismos métodos que Repository pero podrá
                                                            // manipular, hacer cálculos,
    // consultas, insert, interactuar con otros repositorios

    // no es recomendable usar el repository en el controlador directamente porque
    // es donde se persisten los datos sino que se debe usar un service

    // private ProductRepositoryImpl repository = new ProductRepositoryImpl();
    // //esto ya no es necesario porque se usará Autowired

    //@Autowired    //ya no es necesario anotar con Autowired porque en este caso se está haciendo la inyección de dependecia por el constructor
    private ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository){
        this.repository = repository;
    }

    @Override
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

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }
}
