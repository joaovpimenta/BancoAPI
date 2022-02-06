package com.arthur.NextGeneration.controller.resources;


import com.arthur.NextGeneration.model.entities.CartaoCredito;
import com.arthur.NextGeneration.model.services.CartaoCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cartaocreditos")
public class CartaoCreditoResource {

    @Autowired
    private CartaoCreditoService service;

    @GetMapping
    public ResponseEntity<List<CartaoCredito>> findAll() {
        List<CartaoCredito> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CartaoCredito> findById(@PathVariable Long id) {
        CartaoCredito obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
