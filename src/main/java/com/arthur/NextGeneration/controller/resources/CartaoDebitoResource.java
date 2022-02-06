package com.arthur.NextGeneration.controller.resources;


import com.arthur.NextGeneration.model.entities.CartaoDebito;
import com.arthur.NextGeneration.model.services.CartaoDebitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cartaodebitos")
public class CartaoDebitoResource {

    @Autowired
    private CartaoDebitoService service;

    @GetMapping
    public ResponseEntity<List<CartaoDebito>> findAll() {
        List<CartaoDebito> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CartaoDebito> findById(@PathVariable Long id) {
        CartaoDebito obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
