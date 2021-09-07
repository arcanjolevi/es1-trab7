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
import com.main.bo.pessoa.Rg;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class WSEmpresa {

    @PostMapping
    public Empresa hello(@RequestBody Empresa value) {

        Uf uf = new Uf("parase", "PR");

        Cidade cidade = new Cidade("Fiz do asdiaosd", uf);
        TipoLogradouro tipoL = new TipoLogradouro("Brasil", "Av.");
        Logradouro logra = new Logradouro("Av. BrASIL", tipoL);
        Bairro bai = new Bairro("Bairoo do lucas");
        Endereco end = new Endereco("8787687", logra, bai, cidade);

        EnderecoEspecifico endExpe = new EnderecoEspecifico(231, "complemento", end);

        Empresa a = new Empresa("nomeEMpresa", new ArrayList<Telefone>(), new ArrayList<Email>(), endExpe, "486454646");

        // value.vetor.add("valor 1");

        return a;
    }
}
