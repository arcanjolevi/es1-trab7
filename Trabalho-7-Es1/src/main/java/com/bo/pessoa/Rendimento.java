package com.bo.pessoa;

public class Rendimento {
  private Double valor;
  private Double inss;
  private Double iffr;
  private Double decimoTerceiro;
  private Empresa empresa;

  public Rendimento(Double valor, Double inss, Double iffr, Double decimoTerceiro, Empresa empresa) {
    this.valor = valor;
    this.inss = inss;
    this.iffr = iffr;
    this.decimoTerceiro = decimoTerceiro;
    this.empresa = empresa;// Agregação
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public Double getInss() {
    return inss;
  }

  public void setInss(Double inss) {
    this.inss = inss;
  }

  public Double getIffr() {
    return iffr;
  }

  public void setIffr(Double iffr) {
    this.iffr = iffr;
  }

  public Double getDecimoTerceiro() {
    return decimoTerceiro;
  }

  public void setDecimoTerceiro(Double decimoTerceiro) {
    this.decimoTerceiro = decimoTerceiro;
  }

  public Empresa getEmpresa() {
    return empresa;
  }

  public void setEmpresa(Empresa empresa) {
    this.empresa = empresa;
  }

}