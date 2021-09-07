package com.bo.pessoa;

import java.util.ArrayList;

import com.bo.comunicacao.*;
import com.bo.endereco.EnderecoEspecifico;

public class Pessoa {
  private String nome;
  private ArrayList<Telefone> telefones;
  private ArrayList<Email> emails;
  private EnderecoEspecifico enderecoResidencial;

  public Pessoa(String nome, ArrayList<Telefone> telefones, ArrayList<Email> emails,
      EnderecoEspecifico enderecoResidencial) {
    this.nome = nome;
    this.telefones = new ArrayList<>(telefones);
    this.emails = new ArrayList<>(emails);
    this.enderecoResidencial = enderecoResidencial;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public ArrayList<Telefone> getTelefones() {
    return telefones;
  }

  public void setTelefones(ArrayList<Telefone> telefones) {
    this.telefones = new ArrayList<>(telefones);
  }

  public ArrayList<Email> getEmails() {
    return emails;
  }

  public void setEmails(ArrayList<Email> emails) {
    this.emails = new ArrayList<>(emails);
  }

  public EnderecoEspecifico getEnderecoResidencial() {
    return enderecoResidencial;
  }

  public void setEnderecoResidencial(EnderecoEspecifico enderecoResidencial) {
    this.enderecoResidencial = enderecoResidencial;
  }

}