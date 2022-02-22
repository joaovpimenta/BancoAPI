package com.BancoAPI.api;


import com.BancoAPI.model.entities.Pix;
import com.BancoAPI.model.services.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "/api/pixs")
public class PixResource implements Serializable {

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
