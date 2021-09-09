package com.main.view;

import java.util.ArrayList;

import com.main.bo.comunicacao.Telefone;
import com.main.bo.comunicacao.Email;
import com.main.bo.pessoa.Dependente;
import com.main.bo.pessoa.TipoDependente;
import com.main.bo.endereco.Bairro;
import com.main.bo.endereco.Cidade;
import com.main.bo.endereco.Endereco;
import com.main.bo.endereco.EnderecoEspecifico;
import com.main.bo.endereco.Logradouro;
import com.main.bo.endereco.TipoLogradouro;
import com.main.bo.endereco.Uf;

public class DependenteView {
    public String id;
    public String nome;
    public String nomeSocial;
    public String sobreNome;
    public ArrayList<TelefoneView> telefones;
    public ArrayList<String> emails;
    public Integer nroCasa;
    public String complemento;
    public String cep;
    public String nomeLogradouro;
    public String tipoLogradouro;
    public String nomeBairro;
    public String nomeCidade;
    public String nomeUf;
    public String siglaUf;
    public String rg;
    public String cpf;
    public Character sexo;
    public String tipoDependente;
    public String cpfContribuinte;

}
