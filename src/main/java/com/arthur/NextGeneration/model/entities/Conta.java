package com.arthur.NextGeneration.model.entities;


import com.arthur.NextGeneration.model.enums.TipoConta;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Conta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double saldo;
    private String senha;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente = new Cliente();
    private TipoConta tipoConta;
    @OneToOne
    @JoinColumn(name = "cartao_credito_id")
    private CartaoCredito cartaoCredito;
    @OneToOne
    @JoinColumn(name = "cartao_debito_id")
    private CartaoDebito cartaoDebito;
    private double taxa;
    private Date dataTaxa = new Date();
    @Transient
    private boolean correnteBool = false;
    @Transient
    private boolean poupancaBool = false;
    @Transient
    private String saldoString;

    public Conta() {
    }

    public Conta(long id, double saldo, String senha, Cliente cliente, TipoConta tipoConta, CartaoCredito cartaoCredito, CartaoDebito cartaoDebito, double taxa, Date dataTaxa) {
        this.id = id;
        this.saldo = saldo;
        this.senha = senha;
        this.cliente = cliente;
        this.tipoConta = tipoConta;
        this.cartaoCredito = cartaoCredito;
        this.cartaoDebito = cartaoDebito;
        this.taxa = taxa;
        this.dataTaxa = dataTaxa;
    }

    public String getSaldoString() {
        return "R$ " + String.format("%.2f", saldo);
    }

    public void setSaldoString(String saldoString) {
        this.saldoString = "R$ " + String.format(String.valueOf(saldo), "%.2f");
    }

    public boolean isCorrenteBool() {
        return correnteBool;
    }

    public void setCorrenteBool(boolean correnteBool) {
        this.correnteBool = correnteBool;
    }

    public boolean isPoupancaBool() {
        return poupancaBool;
    }

    public void setPoupancaBool(boolean poupancaBool) {
        this.poupancaBool = poupancaBool;
    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public CartaoDebito getCartaoDebito() {
        return cartaoDebito;
    }

    public void setCartaoDebito(CartaoDebito cartaoDebito) {
        this.cartaoDebito = cartaoDebito;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    public Date getDataTaxa() {
        return dataTaxa;
    }

    public void setDataTaxa(Date dataTaxa) {
        this.dataTaxa = dataTaxa;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Conta clone(){
        return new Conta(this.id,this.saldo,this.senha, this.cliente,this.tipoConta,this.cartaoCredito,this.cartaoDebito,this.taxa,this.dataTaxa);
    }

    @Override
    public String toString() {
        return "Nome: " + getCliente().getNome() +
                " CPF: " + getCliente().getCpf() +
                " Conta: " + getTipoConta().name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return id == conta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
