package com.arthur.NextGeneration.model.entities;


import javax.persistence.Entity;

@Entity
public class CartaoDebito extends Cartao {

    private double limitePorTransacao;

    public CartaoDebito() {
    }

    public CartaoDebito(Long id, String bandeira, String senha, double limitePorTransacao) {
        super(id, bandeira, senha, true);
        this.limitePorTransacao = limitePorTransacao;
    }

    public double getLimitePorTransacao() {
        return limitePorTransacao;
    }

    public void setLimitePorTransacao(double limitePorTransacao) {
        this.limitePorTransacao = limitePorTransacao;
    }
}
