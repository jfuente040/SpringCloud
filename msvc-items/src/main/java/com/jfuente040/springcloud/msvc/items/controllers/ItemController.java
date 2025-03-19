package com.jfuente040.springcloud.msvc.items.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jfuente040.springcloud.msvc.items.models.ItemDTO;
import com.jfuente040.springcloud.msvc.items.services.ItemService;

@RestController
@RequestMapping("/api/items")
@RefreshScope
public class ItemController {

    @Value("${config.message}")
    private String configMessage;

    @Value("${config.author}")
    private String author;

    private final ItemService itemService;
    private final Logger log = LoggerFactory.getLogger(ItemController.class);

    @GetMapping("/fetch-configs")
    public ResponseEntity<?> fetchConfigs(@Value("${server.port}") String port) {
        Map<String, String> json = new HashMap<>();
        json.put("text", configMessage);
        json.put("port", port);
        json.put("author", author);
        log.info(port);
        log.info(configMessage);
        log.info(author);
        return ResponseEntity.ok(json);
    } 

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
