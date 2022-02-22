package com.BancoAPI.model.repositories;


import com.BancoAPI.model.entities.Seguro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeguroRepository extends JpaRepository<Seguro, Long> {
}
