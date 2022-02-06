package com.arthur.NextGeneration.model.repositories;


import com.arthur.NextGeneration.model.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
