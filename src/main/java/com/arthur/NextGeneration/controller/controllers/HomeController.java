package com.arthur.NextGeneration.controller.controllers;

import com.arthur.NextGeneration.model.entities.Cliente;
import com.arthur.NextGeneration.model.entities.Conta;
import com.arthur.NextGeneration.model.entities.Endereco;
import com.arthur.NextGeneration.model.enums.TipoConta;
import com.arthur.NextGeneration.model.repositories.ClienteRepository;
import com.arthur.NextGeneration.model.repositories.ContaRepository;
import com.arthur.NextGeneration.model.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class HomeController {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @GetMapping(value = "/")
    public String getHome(){
        return "home";
    }

    @GetMapping(value = "/cadastro")
    public String getCadastrar(Model model){
        Conta conta = new Conta();
        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();
        model.addAttribute("conta", conta);
        model.addAttribute("cliente", cliente);
        model.addAttribute("endereco", endereco);
        return "cadastro";
    }

    @PostMapping(value = "/cadastrar")
    public String insertDados(Conta conta, Cliente cliente, Endereco endereco){
        cliente.setEndereco(endereco);
        conta.setCliente(cliente);
        System.out.println("Chegou aqui");
        System.out.println(conta.isCorrenteBool());
        System.out.println(conta.isPoupancaBool());/*
        if(conta.isCorrenteBool() && conta.isPoupancaBool()){
            Conta conta2 = conta.clone();
            conta.setTipoConta(TipoConta.CORRENTE);
            conta.setTaxa(0.0045);
            conta2.setTipoConta(TipoConta.POUPANCA);
            conta2.setTaxa(0.0003);
            enderecoRepository.saveAll(Arrays.asList(endereco));
            clienteRepository.saveAll(Arrays.asList(cliente));
            contaRepository.saveAll(Arrays.asList(conta,conta2));
            System.out.println("Eh nois");
            return "home";
        }
        else if(conta.isCorrenteBool()){
            conta.setTipoConta(TipoConta.CORRENTE);
            conta.setTaxa(0.0045);
        }else if(conta.isPoupancaBool()){
            conta.setTipoConta(TipoConta.POUPANCA);
            conta.setTaxa(0.0003);
        }else{
            System.out.println("Nenhuma Selecionada");
            return "cadastro";
        }
        enderecoRepository.saveAll(Arrays.asList(endereco));
        clienteRepository.saveAll(Arrays.asList(cliente));
        contaRepository.saveAll(Arrays.asList(conta));*/
        System.out.println("Eh nois");
        return "home";
    }


    @GetMapping(value = "/login")
    public String getLogar(Model model){
        String login = new String();
        String senha = new String();
        model.addAttribute("login", login);
        model.addAttribute("senha", senha);
        return "login";
    }

    @PostMapping(value = "/logar")
    public String login(String login, String senha){
        Conta conta = contaRepository.findByClienteCpf(login);
        if(conta == null){
            System.out.println("Dados Incorretos!");
            return "login";
        }else{
            if(conta.getSenha().equals(senha)){
                System.out.println("Sucesso!");
                return "home";
            }else{
                System.out.println("Dados Incorretos!");
                return "login";
            }
        }
    }
}
