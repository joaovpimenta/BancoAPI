package com.arthur.NextGeneration.model.services;

import com.arthur.NextGeneration.model.entities.Compra;
import com.arthur.NextGeneration.model.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository repository;

    public List<Compra> findAll() {
        return repository.findAll();
    }

    public Compra findById(Long id) {
        Optional<Compra> optional = repository.findById(id);
        return optional.get();
    }
}
