package com.arthur.NextGeneration.model.repositories;


import com.arthur.NextGeneration.model.entities.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Conta findByClienteCpf(String cpf);
}
