package com.main.bo.pessoa;

public class Rendimento {
  private Double valor;
  private Double inss;
  private Double irrf;
  private Double decimoTerceiro;
  private Empresa empresa;

  public Rendimento(Double valor, Double inss, Double irrf, Double decimoTerceiro, Empresa empresa) {
    this.valor = valor;
    this.inss = inss;
    this.irrf = irrf;
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

  public Double getIrrf() {
    return this.irrf;
  }

  public void setIrrf(Double irrf) {
    this.irrf = irrf;
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