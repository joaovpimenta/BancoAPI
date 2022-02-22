package com.BancoAPI.api;


import com.BancoAPI.model.entities.Apolice;
import com.BancoAPI.model.services.ApoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "/api/apolices")
public class ApoliceResource implements Serializable{

    @Autowired
    private ApoliceService service;

    @GetMapping
    public ResponseEntity<List<Apolice>> findAll() {
        List<Apolice> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Apolice> findById(@PathVariable Long id) {
        Apolice obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
