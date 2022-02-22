package com.BancoAPI.model.entities;

import com.BancoAPI.model.enums.TipoChavePix;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Pix implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TipoChavePix chavePix;
    private double valor;
    private String conteudoChave;
    private boolean isAtivado;
    @OneToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public Pix() {
    }

    public Pix(Long id, TipoChavePix chavePix, double valor, String conteudoChave, boolean isAtivado, Conta conta) {
        this.id = id;
        this.chavePix = chavePix;
        this.valor = valor;
        this.conteudoChave = conteudoChave;
        this.isAtivado = isAtivado;
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Long getId() {
        return id;
    }

    public TipoChavePix getChavePix() {
        return chavePix;
    }

    public void setChavePix(TipoChavePix chavePix) {
        this.chavePix = chavePix;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getConteudoChave() {
        return conteudoChave;
    }

    public void setConteudoChave(String conteudoChave) {
        this.conteudoChave = conteudoChave;
    }

    public boolean isAtivado() {
        return isAtivado;
    }

    public void setAtivado(boolean ativado) {
        isAtivado = ativado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pix pix = (Pix) o;
        return id.equals(pix.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
