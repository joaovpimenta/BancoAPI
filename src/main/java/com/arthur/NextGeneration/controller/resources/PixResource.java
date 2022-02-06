package com.arthur.NextGeneration.controller.resources;


import com.arthur.NextGeneration.model.entities.Pix;
import com.arthur.NextGeneration.model.services.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pixs")
public class PixResource {

    @Autowired
    private PixService service;

    @GetMapping
    public ResponseEntity<List<Pix>> findAll() {
        List<Pix> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pix> findById(@PathVariable Long id) {
        Pix obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
