package com.BancoAPI.model.entities;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class CartaoCredito extends Cartao {
    private double limite = 1000;
    private double valorFatura = 0;
    private Date dataVencimento;
    @OneToMany
    @JoinColumn(name = "compra_id")
    private List<Compra> compras = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "apolice_id")
    private List<Apolice> apolices = new ArrayList<>();

    @Transient
    private String dataString;

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        try {
            this.dataVencimento = sdf.parse(dataString);
        } catch (ParseException e) {
            this.dataVencimento = Calendar.getInstance().getTime();
        }
    }

    public CartaoCredito() {
    }

    public CartaoCredito(Long id, String bandeira, String senha, Date dataVencimento) {
        super(id, bandeira, senha, true);
        this.dataVencimento = dataVencimento;
    }

    public void addApolice(Apolice apolice) {
        apolices.add(apolice);
    }

    public List<Apolice> getApolices() {
        return apolices;
    }

    public void setApolices(List<Apolice> apolices) {
        this.apolices = apolices;
    }

    public String dateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        return sdf.format(this.dataVencimento);
    }

    public Date dateAddOneMonth() {
        Calendar calendarAplicacaoTaxa = Calendar.getInstance();
        calendarAplicacaoTaxa.add(Calendar.MONTH, 1);
        return calendarAplicacaoTaxa.getTime();
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public void addCompra(Compra compra) {
        valorFatura += compra.getValor();
        compras.add(compra);
    }

    public String toStringCompras() {
        String resultado = "";
        for (Compra compra : compras) {
            resultado += compra.toString();
            resultado += "\n";
        }
        return resultado;
    }

    public boolean pagarFatura(Conta conta) {
        if (conta.getSaldo() < valorFatura) {
            return false;
        }
        conta.setSaldo(conta.getSaldo() - valorFatura);
        this.valorFatura = 0;
        return true;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getValorFatura() {
        return valorFatura;
    }

    public void setValorFatura(double valorFatura) {
        this.valorFatura = valorFatura;
    }

    public void removeApolice(Apolice apolice){
        apolices.remove(apolice);
    }
}
