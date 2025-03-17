package com.jfuente040.springcloud.msvc.items.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jfuente040.springcloud.msvc.items.clients.ProductFeignClient;
import com.jfuente040.springcloud.msvc.items.models.ItemDTO;
import com.jfuente040.springcloud.msvc.items.models.ProductDTO;

import feign.FeignException;

@Service
public class ItemServiceFeignImpl implements ItemService {

    private final ProductFeignClient client;

    public ItemServiceFeignImpl(ProductFeignClient client) {
        this.client = client;
    }

    @Override
    public List<ItemDTO> findAll() {
        return client.findAll().stream().map(p -> new ItemDTO(p, 1)).toList();
    }

    @Override
    public Optional<ItemDTO> details(Long id) {
        try {
            ProductDTO productDTO = client.details(id);
            return Optional.of(new ItemDTO(productDTO, 1));
        } catch (FeignException e) {
            return Optional.empty();
        }
    }

}
