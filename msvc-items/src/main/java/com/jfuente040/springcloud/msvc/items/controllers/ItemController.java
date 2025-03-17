package com.jfuente040.springcloud.msvc.items.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jfuente040.springcloud.msvc.items.models.ItemDTO;
import com.jfuente040.springcloud.msvc.items.services.ItemService;

@RestController("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    public List<ItemDTO> list() {
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> details(@PathVariable Long id) {        
        Optional<ItemDTO> opt = itemService.details(id);
        if(opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        } else {
            //return ResponseEntity.notFound().build();
            return ResponseEntity.status(404).body(Collections.singletonMap("error", "No existe el producto con id " + id));
        }
    }

}
