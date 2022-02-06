package com.arthur.NextGeneration.model.services;

import com.arthur.NextGeneration.model.entities.Conta;
import com.arthur.NextGeneration.model.entities.Pix;
import com.arthur.NextGeneration.model.enums.TipoChavePix;
import com.arthur.NextGeneration.model.repositories.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Pix getPix() {
        return pix;
    }
}
