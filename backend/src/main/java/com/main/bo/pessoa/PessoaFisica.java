package com.main.bo.pessoa;

import java.util.ArrayList;

import com.main.bo.comunicacao.Email;
import com.main.bo.comunicacao.Telefone;
import com.main.bo.endereco.EnderecoEspecifico;

public class PessoaFisica extends Pessoa {
  private String sobrenome;
  private String nomeSocial;
  private String cpf;
  private String rg;
  private Character sexo;

  public PessoaFisica(String nome, ArrayList<Telefone> telefones, ArrayList<Email> emails,
      EnderecoEspecifico enderecoResidencial, String sobrenome, String nomeSocial, String cpf, String rg,
      Character sexo) {
    super(nome, telefones, emails, enderecoResidencial);
    this.sobrenome = sobrenome;
    this.nomeSocial = nomeSocial;
    this.cpf = cpf;
    this.sexo = sexo;
    this.rg = rg;
  }

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  public String getNomeSocial() {
    return nomeSocial;
  }

  public void setNomeSocial(String nomeSocial) {
    this.nomeSocial = nomeSocial;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getRg() {
    return rg;
  }

  public void setRg(String rg) {
    this.rg = rg;
  }

  public Character getSexo() {
    return sexo;
  }

  public void setSexo(Character sexo) {
    this.sexo = sexo;
  }

}