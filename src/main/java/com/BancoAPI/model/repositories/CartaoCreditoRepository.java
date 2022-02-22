package com.BancoAPI.model.repositories;


import com.BancoAPI.model.entities.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long> {
}
