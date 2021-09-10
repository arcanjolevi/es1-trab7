package com.main.services;

import java.sql.SQLException;
import java.util.ArrayList;

import com.main.bo.comunicacao.Telefone;
import com.main.bo.pessoa.Contribuinte;
import com.main.model.database.DbConnection;
import com.main.view.ContribuinteView;
import com.main.view.TelefoneView;

import com.main.bo.bensedireitos.BensEDireitos;
import com.main.bo.comunicacao.Email;
import com.main.bo.endereco.Bairro;
import com.main.bo.endereco.Cidade;
import com.main.bo.endereco.Endereco;
import com.main.bo.endereco.EnderecoEspecifico;
import com.main.bo.endereco.Logradouro;
import com.main.bo.endereco.TipoLogradouro;
import com.main.bo.endereco.Uf;
import com.main.bo.pessoa.Rendimento;
import com.main.controller.UsContribuinte;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/contribuinte")
public class WSContribuinte {

    @PostMapping
    public ResponseEntity<Integer> criarContribuinte(@RequestBody ContribuinteView view) {
        try {
            Contribuinte contri = this.renderContribuinte(view);
            UsContribuinte contriController = new UsContribuinte();
            Integer id = contriController.Cadastrar(contri);
            return ResponseEntity.status(HttpStatus.OK).body(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("@post /contribuinte - Dados invalidos - Erro 400 - DAD REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<Contribuinte> consultarContribuinte(@RequestParam String cpf) {
        try {
            UsContribuinte contriController = new UsContribuinte();
            Contribuinte contri = contriController.Consultar(cpf);
            return ResponseEntity.status(HttpStatus.OK).body(contri);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("@get /contribuinte - Dados invalidos - Erro 400 - DAD REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.println("@get /contribuinte - Dados invalidos - Erro 400 - DAD REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    public Contribuinte renderContribuinte(ContribuinteView view) {
        ArrayList<Telefone> telefones = new ArrayList<Telefone>();
        for (TelefoneView t : view.telefones) {
            telefones.add(new Telefone(t.getDdd(), t.getDdi(), t.getNumero()));
        }

        ArrayList<Email> emails = new ArrayList<Email>();
        for (String e : view.emails) {
            emails.add(new Email(e));
        }

        TipoLogradouro tipoLogradouro = new TipoLogradouro(view.nomeLogradouro, view.tipoLogradouro);
        Logradouro logradouro = new Logradouro(view.nomeLogradouro, tipoLogradouro);

        Uf uf = new Uf(view.nome, view.siglaUf);

        Cidade cidade = new Cidade(view.nomeCidade, uf);
        Bairro bairro = new Bairro(view.nomeBairro);
        Endereco endereco = new Endereco(view.cep, logradouro, bairro, cidade);
        EnderecoEspecifico enderecoResidencial = new EnderecoEspecifico(view.nroCasa, view.complemento, endereco);
        ArrayList<BensEDireitos> bens = new ArrayList<BensEDireitos>();
        ArrayList<Rendimento> rendimentos = new ArrayList<Rendimento>();
        Contribuinte con = new Contribuinte(view.nome, telefones, emails, enderecoResidencial, view.sobreNome,
                view.nomeSocial, view.cpf, view.rg, view.sexo, bens, rendimentos);

        return con;
    }

}
