package com.BancoAPI.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

@Entity
public abstract class Cartao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String bandeira;
    private String senha;
    private boolean isAtivo = false;

    protected Cartao() {
    }

    protected Cartao(Long id, String bandeira, String senha, boolean isAtivo) {
        this.id = id;
        this.numero = setNumeroRandom();
        this.bandeira = bandeira;
        this.senha = senha;
        this.isAtivo = isAtivo;
    }

    private String setNumeroRandom(){
        Random random = new Random();
        String novoNumero = "";
        int numeroDaVez = 0;
        for(int i = 0; i < 4; i++){
            numeroDaVez = random.nextInt(10000);
            if(numeroDaVez<10){
                novoNumero += "000";
            }else if(numeroDaVez<100){
                novoNumero += "00";
            }else if(numeroDaVez<1000){
                novoNumero += "0";
            }
            novoNumero += String.valueOf(numeroDaVez);
            novoNumero += " ";
        }
        return novoNumero.strip();
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean ativo) {
        isAtivo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartao cartao = (Cartao) o;
        return id.equals(cartao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
