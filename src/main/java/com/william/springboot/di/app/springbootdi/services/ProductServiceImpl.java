package com.william.springboot.di.app.springbootdi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.william.springboot.di.app.springbootdi.models.Product;
import com.william.springboot.di.app.springbootdi.repositories.ProductRepository;

@Service // indica que es un componente de java
public class ProductServiceImpl implements ProductService { // tiene los mismos métodos que Repository pero podrá
                                                            // manipular, hacer cálculos,
    // consultas, insert, interactuar con otros repositorios

    // no es recomendable usar el repository en el controlador directamente porque
    // es donde se persisten los datos sino que se debe usar un service

    // private ProductRepositoryImpl repository = new ProductRepositoryImpl();
    // //esto ya no es necesario porque se usará Autowired

    //@Autowired    //ya no es necesario anotar con Autowired porque en este caso se está haciendo la inyección de dependecia por el constructor
    //@Qualifier("productFoo")
    private ProductRepository repository;

    //@Autowired
    //private Environment environment;    //esto se podría agregar como parámetro al constructor

    @Value("${config.price.tax}")
    private Double tax;

    public ProductServiceImpl(@Qualifier("productList") ProductRepository repository){  //este @Qualifier("productList") tiene el nombre de lo que se definió en @Repository de ProductRepositoryImpl, si no tuviera un nombre, va el nombre de la clase iniciando con minúscula. @Qualifier no es necesario si ya se ha implementado un @Primary y se quiere usar ese
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(p -> {
            //Double priceTemp = p.getPrice() * environment.getProperty("config.price.tax", Double.class);  //tabajando con environment
            Double priceTax = p.getPrice() * tax;

            // Product newProd = new Product(p.getId(), p.getName(), priceTemp.longValue());
            // principio de inmutabilidad, se está creando un nuevo objeto para que este no
            // se modifique v2

            // p.setPrice(priceTemp.longValue()); //v1
            // return p; //v1
            Product newProd = (Product) p.clone(); // v3 clone() devuelve la nueva instancia
            newProd.setPrice(priceTax.longValue());
            return newProd;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }
}
