package com.arthur.NextGeneration.model.services;

import com.arthur.NextGeneration.model.entities.Apolice;
import com.arthur.NextGeneration.model.repositories.ApoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApoliceService {

    @Autowired
    private ApoliceRepository repository;

    public List<Apolice> findAll() {
        return repository.findAll();
    }

    public Apolice findById(Long id) {
        Optional<Apolice> optional = repository.findById(id);
        return optional.get();
    }
}
