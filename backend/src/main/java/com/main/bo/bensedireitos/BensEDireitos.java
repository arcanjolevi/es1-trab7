package com.main.bo.bensedireitos;

public class BensEDireitos {
  private Double valor;
  private TipoBensEDireitos tipoBem;

  public BensEDireitos(Double valor, TipoBensEDireitos tipoBem) {
    this.valor = valor;
    this.tipoBem = new TipoBensEDireitos(tipoBem);
  }

  public Double getValor() {
    return valor;
  }

  public TipoBensEDireitos getTipoBem() {
    return tipoBem;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public void setTipoBem(TipoBensEDireitos tipoBem) {
    this.tipoBem = tipoBem;
  }

}