package com.main.services;

import java.util.ArrayList;

import com.main.bo.comunicacao.Email;
import com.main.bo.comunicacao.Telefone;
import com.main.bo.endereco.Bairro;
import com.main.bo.endereco.Cidade;
import com.main.bo.endereco.Endereco;
import com.main.bo.endereco.EnderecoEspecifico;
import com.main.bo.endereco.Logradouro;
import com.main.bo.endereco.TipoLogradouro;
import com.main.bo.endereco.Uf;
import com.main.bo.pessoa.Empresa;
import com.main.json.EmpresaJson;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class WSEmpresa {

    @PostMapping
    public Empresa criarEmpresa(@RequestBody EmpresaJson value) {

        Uf uf = new Uf(value.nomeUf, value.siglaUf);

        Cidade cidade = new Cidade(value.nomeCidade, uf);
        TipoLogradouro tipoLogradouro = new TipoLogradouro(value.nomeLogradouro, value.tipoLogradouro);
        Logradouro logradouro = new Logradouro(value.tipoLogradouro + ". " + value.nomeLogradouro, tipoLogradouro);
        Bairro bairro = new Bairro(value.nomeBairro);
        Endereco endereco = new Endereco(value.cep, logradouro, bairro, cidade);

        EnderecoEspecifico endExpe = new EnderecoEspecifico(231, value.complemento, endereco);

        Empresa a = new Empresa(value.nome, new ArrayList<Telefone>(), new ArrayList<Email>(), endExpe, value.cnpj);
        
        // value.vetor.add("valor 1");

        return a;
    }


    public Empresa consultarEmpresa(@RequestBody EmpresaJson value){

        return null;

    }
}
