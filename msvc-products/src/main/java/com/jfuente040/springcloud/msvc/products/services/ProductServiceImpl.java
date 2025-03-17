package com.jfuente040.springcloud.msvc.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfuente040.springcloud.msvc.products.entities.Product;
import com.jfuente040.springcloud.msvc.products.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Environment environment;

    public ProductServiceImpl(ProductRepository productRepository, Environment environment) {
        this.environment = environment;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return ((List<Product>)productRepository.findAll()).stream().map(p -> {
            p.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return p;
        }).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id).map(p -> {
            p.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return p;
        });
    }
    

}
