package com.bo.pessoa;

import java.util.ArrayList;

import com.bo.comunicacao.Email;
import com.bo.comunicacao.Telefone;
import com.bo.endereco.EnderecoEspecifico;

public class Empresa extends PessoaJuridica {

  public Empresa(String nome, ArrayList<Telefone> telefones, ArrayList<Email> emails,
      EnderecoEspecifico enderecoResidencial, String cnpj) {
    super(nome, telefones, emails, enderecoResidencial, cnpj);
  }

}