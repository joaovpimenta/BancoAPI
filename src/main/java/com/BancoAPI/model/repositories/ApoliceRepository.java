package com.BancoAPI.model.repositories;


import com.BancoAPI.model.entities.Apolice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApoliceRepository extends JpaRepository<Apolice, Long> {
}
