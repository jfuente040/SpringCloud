package com.jfuente040.springcloud.msvc.items.services;

import java.util.List;
import java.util.Optional;

import com.jfuente040.springcloud.msvc.items.models.ItemDTO;

public interface ItemService {

    List<ItemDTO> findAll();

    Optional<ItemDTO> details(Long id);

}
