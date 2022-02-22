package com.BancoAPI.api;


import com.BancoAPI.model.entities.Seguro;
import com.BancoAPI.model.services.SeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "/api/segurosDados")
public class SeguroResource implements Serializable {

    @Autowired
    private SeguroService service;

    @GetMapping
    public ResponseEntity<List<Seguro>> findAll() {
        List<Seguro> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Seguro> findById(@PathVariable Long id) {
        Seguro obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
