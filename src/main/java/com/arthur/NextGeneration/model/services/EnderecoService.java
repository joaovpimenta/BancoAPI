package com.arthur.NextGeneration.model.services;

import com.arthur.NextGeneration.model.entities.Endereco;
import com.arthur.NextGeneration.model.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public List<Endereco> findAll() {
        return repository.findAll();
    }

    public Endereco findById(Long id) {
        Optional<Endereco> optional = repository.findById(id);
        return optional.get();
    }
}
