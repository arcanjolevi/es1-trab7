package com.bo.pessoa;

import java.util.Date;

public class Rg {
  private String nroRg;
  private Date dataEmissao;

  public Rg(String nroRg, Date dataEmissao) {
    this.nroRg = nroRg;
    this.dataEmissao = dataEmissao;
  }

  public String getNroRg() {
    return nroRg;
  }

  public void setNroRg(String nroRg) {
    this.nroRg = nroRg;
  }

  public Date getDataEmissao() {
    return dataEmissao;
  }

  public void setDataEmissao(Date dataEmissao) {
    this.dataEmissao = dataEmissao;
  }

}
