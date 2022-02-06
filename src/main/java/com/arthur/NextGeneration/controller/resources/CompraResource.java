package com.arthur.NextGeneration.controller.resources;


import com.arthur.NextGeneration.model.entities.Compra;
import com.arthur.NextGeneration.model.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/compras")
public class CompraResource {

    @Autowired
    private CompraService service;

    @GetMapping
    public ResponseEntity<List<Compra>> findAll() {
        List<Compra> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Compra> findById(@PathVariable Long id) {
        Compra obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
