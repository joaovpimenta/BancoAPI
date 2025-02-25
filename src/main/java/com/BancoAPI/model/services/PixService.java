package com.BancoAPI.model.services;

import com.BancoAPI.model.entities.Conta;
import com.BancoAPI.model.entities.Pix;
import com.BancoAPI.model.enums.TipoChavePix;
import com.BancoAPI.model.repositories.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PixService {

    @Autowired
    private PixRepository repository;

    private Pix pix = new Pix();

    public PixService(){}

    public PixService(Pix pix){
        this.pix = pix;
    }


    // Search Methods


    public List<Pix> findAll() {
        return repository.findAll();
    }

    public Pix findById(Long id) {
        Optional<Pix> optional = repository.findById(id);
        return optional.get();
    }


    // Validation Methods


    public boolean ativarChave(TipoChavePix chavePix, double valor, String conteudoChave, Conta conta){
        if(conta == null){
            return false;
        }
        pix.setChavePix(chavePix);
        pix.setValor(valor);
        pix.setConteudoChave(conteudoChave);
        pix.setAtivado(true);
        pix.setConta(conta);
        return true;
    }

    public String gerarRandomNumber(){
        Random random = new Random();
        return String.valueOf(random.nextInt(99999999));
    }

    public Pix getPix() {
        return pix;
    }
}
