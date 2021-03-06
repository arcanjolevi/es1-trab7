package com.main.bo.bensedireitos;

public class TipoBensEDireitos {
  private String nome;
  private String descricao;

  public TipoBensEDireitos(String nome, String descricao) {
    this.nome = nome;
    this.descricao = descricao;
  }

  public TipoBensEDireitos(TipoBensEDireitos other) {
    this.nome = other.nome;
    this.descricao = other.descricao;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

}