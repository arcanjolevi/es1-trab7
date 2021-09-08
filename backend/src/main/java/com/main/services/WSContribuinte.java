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
import com.main.bo.pessoa.Contribuinte;
import com.main.bo.pessoa.Empresa;
import com.main.json.ContribuinteJson;
import com.main.json.EmpresaJson;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ambiente")
public class WSContribuinte {

    @PostMapping
    public Contribuinte criarAmbiente(@RequestBody ContribuinteJson value) {
        
      
        return null;
    }


    public Empresa consultarContribuinte(@RequestBody ContribuinteJson value){

        return null;

    }
}
