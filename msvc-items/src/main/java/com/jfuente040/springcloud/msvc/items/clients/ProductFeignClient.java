package com.jfuente040.springcloud.msvc.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jfuente040.springcloud.msvc.items.models.ProductDTO;

@FeignClient(name = "msvc-products", path = "/api/products")
//@FeignClient(name = "msvc-products", url = "localhost:56896/api/products")
public interface ProductFeignClient {

    @GetMapping
    List<ProductDTO> findAll();


    @GetMapping("/{id}")
    ProductDTO details(@PathVariable Long id);

} 
