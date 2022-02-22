package com.BancoAPI.model.services;

import com.BancoAPI.model.entities.CartaoCredito;
import com.BancoAPI.model.repositories.CartaoCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaoCreditoService {

    @Autowired
    private CartaoCreditoRepository repository;

    public List<CartaoCredito> findAll() {
        return repository.findAll();
    }

    public CartaoCredito findById(Long id) {
        Optional<CartaoCredito> optional = repository.findById(id);
        return optional.get();
    }
}
