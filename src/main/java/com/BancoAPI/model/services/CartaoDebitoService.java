package com.BancoAPI.model.services;

import com.BancoAPI.model.entities.CartaoDebito;
import com.BancoAPI.model.repositories.CartaoDebitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaoDebitoService {

    @Autowired
    private CartaoDebitoRepository repository;

    public List<CartaoDebito> findAll() {
        return repository.findAll();
    }

    public CartaoDebito findById(Long id) {
        Optional<CartaoDebito> optional = repository.findById(id);
        return optional.get();
    }
}
