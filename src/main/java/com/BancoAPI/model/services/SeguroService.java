package com.BancoAPI.model.services;

import com.BancoAPI.model.entities.Seguro;
import com.BancoAPI.model.repositories.SeguroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeguroService {

    @Autowired
    private SeguroRepository repository;

    public List<Seguro> findAll() {
        return repository.findAll();
    }

    public Seguro findById(Long id) {
        Optional<Seguro> optional = repository.findById(id);
        return optional.get();
    }
}
