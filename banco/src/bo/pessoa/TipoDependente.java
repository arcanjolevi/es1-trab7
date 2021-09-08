package src.bo.pessoa;

public class TipoDependente {
  private String tipoDependente;

  public TipoDependente(String tipoDependente) {
    this.tipoDependente = tipoDependente;
  }

  public TipoDependente(TipoDependente other) {
    this.tipoDependente = other.tipoDependente;
  }

  public String getTipoDependente() {
    return tipoDependente;
  }

  public void setTipoDependente(String tipoDependente) {
    this.tipoDependente = tipoDependente;
  }

}