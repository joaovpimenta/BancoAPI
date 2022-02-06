package com.arthur.NextGeneration.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeProduto;
    private Date compra;
    private double valor;

    public Compra() {
    }

    public Compra(Long id, String nomeProduto, double valor) {
        this.id = id;
        Calendar calendario = Calendar.getInstance();
        Date dataCompra = calendario.getTime();
        this.nomeProduto = nomeProduto;
        this.compra = dataCompra;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Date getCompra() {
        return compra;
    }

    public void setCompra(Date compra) {
        this.compra = compra;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataCompra = fmt.format(compra);
        return "Data da Compra: " + dataCompra +
                ", Valor: " + valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return id.equals(compra.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
