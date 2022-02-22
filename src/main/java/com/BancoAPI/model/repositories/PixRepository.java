package com.BancoAPI.model.repositories;


import com.BancoAPI.model.entities.Conta;
import com.BancoAPI.model.entities.Pix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface PixRepository extends JpaRepository<Pix, Long> {

    ArrayList<Pix> findAllByConta(Conta conta);
    Pix findByConteudoChave(String conteudoChave);
    Pix findByContaClienteCpf(String cpf);
    Pix findByContaClienteEmail(String cpf);
    Pix findByContaClienteTelefone(String cpf);
}
