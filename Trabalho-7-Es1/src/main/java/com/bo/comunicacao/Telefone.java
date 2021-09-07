package com.bo.comunicacao;

public class Telefone {
  private String ddd;
  private String ddi;
  private String numero;

  public Telefone(String ddd, String ddi, String numero) {
    this.ddd = ddd;
    this.ddi = ddi;
    this.numero = numero;
  }

  public String getDdd() {
    return ddd;
  }

  public void setDdd(String ddd) {
    this.ddd = ddd;
  }

  public String getDdi() {
    return ddi;
  }

  public void setDdi(String ddi) {
    this.ddi = ddi;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

}