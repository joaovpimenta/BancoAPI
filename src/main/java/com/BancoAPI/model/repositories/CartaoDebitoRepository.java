package com.BancoAPI.model.repositories;


import com.BancoAPI.model.entities.CartaoDebito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoDebitoRepository extends JpaRepository<CartaoDebito, Long> {
}
