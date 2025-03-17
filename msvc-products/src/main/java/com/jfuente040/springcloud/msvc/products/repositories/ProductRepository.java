package com.jfuente040.springcloud.msvc.products.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jfuente040.springcloud.msvc.products.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
