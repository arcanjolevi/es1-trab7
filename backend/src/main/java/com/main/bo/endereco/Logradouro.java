package com.main.bo.endereco;

public class Logradouro {
  private String nome;
  private TipoLogradouro tipoLogradouro;

  public Logradouro(String nome, TipoLogradouro tipoLogradouro) {
    this.nome = nome;
    this.tipoLogradouro = new TipoLogradouro(tipoLogradouro);
  }

  public Logradouro(Logradouro other) {
    this.nome = other.nome;
    this.tipoLogradouro = new TipoLogradouro(other.tipoLogradouro);
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public TipoLogradouro getTipoLogradouro() {
    return tipoLogradouro;
  }

  public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
    this.tipoLogradouro = tipoLogradouro;
  }

}