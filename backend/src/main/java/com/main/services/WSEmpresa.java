package com.main.services;

import java.util.ArrayList;

import com.main.bo.pessoa.Empresa;
import com.main.controller.UsEmpresa;
import com.main.view.EmpresaView;
import com.main.view.TelefoneView;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.bo.comunicacao.Email;
import com.main.bo.comunicacao.Telefone;
import com.main.bo.endereco.Bairro;
import com.main.bo.endereco.Cidade;
import com.main.bo.endereco.Endereco;
import com.main.bo.endereco.EnderecoEspecifico;
import com.main.bo.endereco.Logradouro;
import com.main.bo.endereco.TipoLogradouro;
import com.main.bo.endereco.Uf;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/empresa")
public class WSEmpresa {

    @PostMapping
    public ResponseEntity<Integer> criarEmpresa(@RequestBody EmpresaView empresaView) {

        try {
            Empresa empresa = this.renderEmpresa(empresaView);
            UsEmpresa empresaController = new UsEmpresa();
            Integer idEmpresa = empresaController.Cadastrar(empresa);
            return ResponseEntity.status(HttpStatus.OK).body(idEmpresa);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("@post /empresa - Dados invalidos - Erro 400 - DAD REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<Empresa> consultarEmpresa(@RequestParam String cnpj) {
        UsEmpresa empresaController = new UsEmpresa();
        Empresa empresa;
        try {
            empresa = empresaController.Consultar(cnpj);
            return ResponseEntity.status(HttpStatus.OK).body(empresa);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    public Empresa renderEmpresa(EmpresaView view) {

        ArrayList<Telefone> telefones = new ArrayList<Telefone>();
        for (TelefoneView t : view.telefones) {
            telefones.add(new Telefone(t.getDdd(), t.getDdi(), t.getNumero()));
        }
        ArrayList<Email> emails = new ArrayList<Email>();
        for (String t : view.emails) {
            emails.add(new Email(t));
        }

        Uf uf = new Uf(view.nomeUf, view.siglaUf);
        Cidade cidade = new Cidade(view.nomeCidade, uf);
        TipoLogradouro tipoLogradouro = new TipoLogradouro(view.nomeLogradouro, view.tipoLogradouro);
        Logradouro logradouro = new Logradouro(view.tipoLogradouro + ". " + view.nomeLogradouro, tipoLogradouro);
        Bairro bairro = new Bairro(view.nomeBairro);
        Endereco endereco = new Endereco(view.cep, logradouro, bairro, cidade);
        EnderecoEspecifico endExpe = new EnderecoEspecifico(view.nroCasa, view.complemento, endereco);
        Empresa empresa = new Empresa(view.nome, telefones, emails, endExpe, view.cnpj);
        return empresa;
    }

}
