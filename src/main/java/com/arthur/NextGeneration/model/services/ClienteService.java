package com.arthur.NextGeneration.model.services;

import com.arthur.NextGeneration.model.entities.Cliente;
import com.arthur.NextGeneration.model.entities.Endereco;
import com.arthur.NextGeneration.model.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    private Cliente cliente = new Cliente();

    public ClienteService(){}

    public ClienteService(String cpf, String nome, Date dataDeNascimento, Endereco endereco, String email, String telefone) {
        cadastrarDados(nome, cpf, dataDeNascimento, endereco, email, telefone);
    }

    public ClienteService(Cliente cliente) {
        this.cliente = cliente;
    }

    // Search Methods

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente findById(Long id) {
        Optional<Cliente> optional = repository.findById(id);
        return optional.get();
    }

    // Validation Methods

    public static boolean checkCPF(String cpf) {
        if (cpf.length() == 11) {
            for (int i = 0; i < cpf.length(); i++) {
                if (!Character.isDigit(cpf.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private void cadastrarDados(String nome, String cpf, Date dataDeNascimento, Endereco endereco, String email, String telefone) {
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setDataDeNascimento(dataDeNascimento);
        cliente.setEndereco(endereco);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
    }

}
