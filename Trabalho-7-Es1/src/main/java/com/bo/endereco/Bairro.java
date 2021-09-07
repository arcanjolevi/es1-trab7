package com.bo.endereco;

public class Bairro {
  private String nome;

  public Bairro(String nome) {
    this.nome = nome;
  }

  public Bairro(Bairro other) {
    this.nome = other.nome;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

}