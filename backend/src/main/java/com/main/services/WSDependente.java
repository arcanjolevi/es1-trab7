package com.main.services;

import java.util.ArrayList;

import com.main.bo.pessoa.Dependente;
import com.main.view.DependenteView;
import com.main.view.TelefoneView;
import com.main.bo.comunicacao.Telefone;
import com.main.bo.comunicacao.Email;
import com.main.bo.pessoa.TipoDependente;
import com.main.controller.UsDependente;
import com.main.bo.endereco.Bairro;
import com.main.bo.endereco.Cidade;
import com.main.bo.endereco.Endereco;
import com.main.bo.endereco.EnderecoEspecifico;
import com.main.bo.endereco.Logradouro;
import com.main.bo.endereco.TipoLogradouro;
import com.main.bo.endereco.Uf;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dependente")
public class WSDependente {

    @PostMapping
    public ResponseEntity<Integer> criarDependente(@RequestBody DependenteView depeView) {
        try {
            Dependente dep = this.renderDependente(depeView);
            UsDependente depeController = new UsDependente();
            Integer id = depeController.Cadastrar(dep, depeView.cpfContribuinte);
            return ResponseEntity.status(HttpStatus.OK).body(id);
        } catch (Exception e) {
            System.out.println("@post /dependente - Dados invalidos - Erro 400 - BAD REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping("/cpf")
    @GetMapping
    public ResponseEntity<Dependente> consultarDependente(@RequestParam String cpf) {
        UsDependente depeController = new UsDependente();
        try {
            Dependente dependente = depeController.Consultar(cpf);
            return ResponseEntity.status(HttpStatus.OK).body(dependente);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("@get /dependente/consulta/cpf - Dados invalidos - Erro 400 - BAD REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping("/remove/{cpf}")
    @PostMapping
    public DependenteView removerDependente(@RequestParam String cpf) {
        UsDependente depeController = new UsDependente();

        return null;
    }

    @RequestMapping("/listar/todos/cpf")
    @GetMapping
    public ResponseEntity<ArrayList<Dependente>> consultarTiposDependente(@RequestParam String cpf) {

        UsDependente depeController = new UsDependente();
        depeController.Consultar(cpf);

        return new ArrayList<String>();
    }

    public Dependente renderDependente(DependenteView view) {
        ArrayList<Telefone> tells = new ArrayList<Telefone>();

        for (TelefoneView t : view.telefones) {
            tells.add(new Telefone(t.getDdd(), t.getDdi(), t.getNumero()));
        }

        ArrayList<Email> emailsArray = new ArrayList<Email>();
        for (String e : view.emails) {
            emailsArray.add(new Email(e));
        }

        TipoLogradouro tipoLogradouro = new TipoLogradouro(view.nomeLogradouro, view.tipoLogradouro);
        Logradouro logradouro = new Logradouro(view.nomeLogradouro, tipoLogradouro);

        Uf uf = new Uf(view.nome, view.siglaUf);

        Cidade cidade = new Cidade(view.nomeCidade, uf);
        Bairro bairro = new Bairro(view.nomeBairro);
        Endereco endereco = new Endereco(view.cep, logradouro, bairro, cidade);
        EnderecoEspecifico enderecoResidencial = new EnderecoEspecifico(view.nroCasa, view.complemento, endereco);
        TipoDependente tipo = new TipoDependente(view.tipoDependente);
        Dependente dependente = new Dependente(view.nome, tells, emailsArray, enderecoResidencial, view.sobreNome,
                view.nomeSocial, view.cpf, view.rg, view.sexo, tipo);

        return dependente;
    }
}
