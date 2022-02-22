package com.BancoAPI.model.repositories;


import com.BancoAPI.model.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Conta findByClienteCpf(String cpf);

    ArrayList<Conta> findAllByClienteCpf(String cpf);
}
