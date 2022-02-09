package com.arthur.NextGeneration.controller.controllers;

import com.arthur.NextGeneration.model.entities.Cliente;
import com.arthur.NextGeneration.model.entities.Conta;
import com.arthur.NextGeneration.model.entities.Endereco;
import com.arthur.NextGeneration.model.enums.TipoConta;
import com.arthur.NextGeneration.model.repositories.ClienteRepository;
import com.arthur.NextGeneration.model.repositories.ContaRepository;
import com.arthur.NextGeneration.model.repositories.EnderecoRepository;
import com.arthur.NextGeneration.model.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class HomeController {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    Conta contaLogada;

    @GetMapping(value = "/")
    public String getHome(){
        return "home";
    }

    @GetMapping(value = "/cadastro")
    public String getCadastrar(ModelMap model){
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
        contaRepository.saveAll(Arrays.asList(conta));
        System.out.println("Eh nois");
        return "home";
    }


    @GetMapping(value = "/login")
    public String getLogar(Model model){
        String login = new String();
        String senha = new String();
        Boolean corrente = false;
        Boolean poupanca = false;
        model.addAttribute("login", login);
        model.addAttribute("senha", senha);
        model.addAttribute("corrente", corrente);
        model.addAttribute("poupanca", poupanca);
        return "login";
    }

    @PostMapping(value = "/logar")
    public String login(String login, String senha, Boolean corrente, Boolean poupanca){
        ArrayList<Conta> list = contaRepository.findAllByClienteCpf(login);
        if(list.isEmpty() || list == null){
            System.out.println("Não achou nada...");
            return "login";
        }
        if(corrente == null){
            corrente = false;
        }
        if(poupanca == null){
            poupanca = false;
        }
        Conta conta = new Conta();
        for(Conta contaVer: list){
            if(contaVer.getTipoConta().equals(TipoConta.CORRENTE) && corrente == true){
                System.out.println("Achei sua conta!");
                conta = contaVer;
                break;
            }else if(contaVer.getTipoConta().equals(TipoConta.POUPANCA) && poupanca == true){
                System.out.println("Achei sua conta!");
                conta = contaVer;
                break;
            }
        }
        if(conta == null){
            System.out.println("Dados Incorretos!");
            return "login";
        }else{
            if(conta.getSenha().equals(senha)){
                System.out.println("Sucesso!");
                contaLogada = conta;
                return "redirect:/menu";
            }else{
                System.out.println("Dados Incorretos!");
                return "login";
            }
        }
    }

    @GetMapping(value = "/menu")
    public String getLogado(ModelMap model){
        if(contaLogada == null){
            return "redirect:/";
        }
        String cpfExterno = new String();

        model.addAttribute("contaLogado", contaLogada);
        model.addAttribute("cpfExterno",cpfExterno);
        return "iniciar";
    }

    // ENTRE RENDERIZAR A PÁGINA E LOGIN
    // MENU INICIAL NÃO POSSUI @GETMAPPING NEM @POSTMAPPING E FUNCIONOU

    // Mapeando TRANSAÇÕES

    @GetMapping(value = "/transacoes")
    public String getTransacoes(ModelMap model){
        String valorDeposito = new String();
        String cpfExterno = new String();
        Boolean corrente = false;
        Boolean poupanca = false;

        model.addAttribute("valorDeposito", valorDeposito);
        model.addAttribute("corrente", corrente);
        model.addAttribute("poupanca", poupanca);
        model.addAttribute("contaLogadinha", contaLogada);
        model.addAttribute("cpfExterno",cpfExterno);
        if(contaLogada == null){
            return "redirect:/";
        }
        return "transacoes";
    }

    @PostMapping (value = "/godepositar")
    public String godepositar(Model model){
        model.addAttribute("contasuprema",contaLogada);
        return "depositar";
    }

    @PostMapping (value = "/gosacar")
    public String gosacar(Model model){
        model.addAttribute("contasuprema",contaLogada);
        return "saque";
    }

    @PostMapping (value = "/gotransferir")
    public String gotransferir(Model model){
        model.addAttribute("contasuprema",contaLogada);
        return "transferir";
    }

    @GetMapping(value = "/depositare")
    public String getDeposito(Conta contaLogadinha){
        if(contaLogada == null){
            return "redirect:/";
        }
        System.out.println(contaLogadinha);
        return "depositar";
    }

    @PostMapping (value = "/depositar")
    public String depositar(String valorDeposito, String login){
        contaLogada.setSaldo(contaLogada.getSaldo() + Double.parseDouble(valorDeposito));
        System.out.println(contaLogada.getSaldo());
        contaRepository.save(contaLogada);
        return "redirect:/menu";
    }

    @GetMapping(value = "/menu/transacoes/sacar/")
    public String getSaque(){
        if(contaLogada == null){
            return "redirect:/";
        }
        return "saque";
    }

    @PostMapping(value = "/sacar")
    public String postSacar(String valorDeposito){
        contaLogada.setSaldo(contaLogada.getSaldo() - Double.parseDouble(valorDeposito));
        System.out.println(contaLogada.getSaldo());
        contaRepository.save(contaLogada);
        return "redirect:/menu";
    }

    @GetMapping(value = "/menu/transacoes/transferir/")
    public String getTransferencia(ModelMap model){
        if(contaLogada == null){
            return "redirect:/";
        }
        return "transferir"; // Página HTML
    }

    @PostMapping(value = "/transferir")
    public String postTransferir(String valorDeposito, String cpfExterno, Boolean corrente, Boolean poupanca){
        ArrayList<Conta> list = contaRepository.findAllByClienteCpf(cpfExterno);
        if(list.isEmpty() || list == null){
            System.out.println("Não achou nada...");
            return "redirect:/menu/transacoes/transferir/";
        }
        if(corrente == null){
            corrente = false;
        }
        if(poupanca == null){
            poupanca = false;
        }
        Conta conta = new Conta();
        for(Conta contaVer: list){
            if(contaVer.getTipoConta().equals(TipoConta.CORRENTE) && corrente == true){
                System.out.println("Achei sua conta!");
                conta = contaVer;
                break;
            }else if(contaVer.getTipoConta().equals(TipoConta.POUPANCA) && poupanca == true){
                System.out.println("Achei sua conta!");
                conta = contaVer;
                break;
            }
        }
        if(conta == null){
            System.out.println("Dados Incorretos!");
            return "redirect:/menu/transacoes/transferir/";
        }
        ContaService contaProcessos = new ContaService(contaLogada);
        System.out.println(valorDeposito);
        if(!contaProcessos.transferir(Double.parseDouble(valorDeposito), conta)){
            System.out.println("Saldo Insuficiente");
            return "redirect:/menu/transacoes/transferir/";
        }
        contaRepository.save(contaLogada);
        contaRepository.save(conta);
        return "redirect:/menu";
    }




    // -------------------------- É HORA DO PIX -------------------------------------





    @GetMapping(value = "/menu/pix/")
    public String getPix(){
        if(contaLogada == null){
            return "redirect:/";
        }
        return "pix";
    }

    @GetMapping(value = "/menu/pix/cadastrarpix/")
    public String getCadastroPix(){
        if(contaLogada == null){
            return "redirect:/";
        }
        return "cadastrapix";
    }

    @PostMapping(value = "/gocadastrarpix")
    public String goCadastrarPix(ModelMap model){
        model.addAttribute("contasuprema",contaLogada);
        return "cadastrapix";
    }

    @PostMapping(value = "/goconsultarpix")
    public String goConsultarPix(ModelMap model){
        model.addAttribute("contasuprema",contaLogada);
        return "consultachave";
    }

    @PostMapping(value = "/gotransferirpix")
    public String goTransferirPix(ModelMap model){
        model.addAttribute("contasuprema",contaLogada);
        return "tranferirpix";
    }

    @GetMapping(value = "/menu/pix/consultarchave/")
    public String getConsultarPix(){
        if(contaLogada == null){
            return "redirect:/";
        }
        return "consultachave";
    }

    @GetMapping(value = "/menu/pix/transferir/")
    public String getTransferirPix(){
        if(contaLogada == null){
            return "redirect:/";
        }
        return "transferirpix";
    }

}
