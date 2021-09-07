package com.bo.pessoa;

import java.util.ArrayList;

import com.bo.comunicacao.Email;
import com.bo.comunicacao.Telefone;
import com.bo.endereco.EnderecoEspecifico;

public class PessoaJuridica extends Pessoa {
  private String cnpj;

  public PessoaJuridica(String nome, ArrayList<Telefone> telefones, ArrayList<Email> emails,
      EnderecoEspecifico enderecoResidencial, String cnpj) {
    super(nome, telefones, emails, enderecoResidencial);
    this.cnpj = cnpj;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

}