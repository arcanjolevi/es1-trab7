package src.bo.endereco;

public class Uf {
  private String nome;
  private String sigla;

  public Uf(String nome, String sigla) {
    this.nome = nome;
    this.sigla = sigla;
  }

  public Uf(Uf other) {
    this.nome = other.nome;
    this.sigla = other.sigla;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSigla() {
    return sigla;
  }

  public void setSigla(String sigla) {
    this.sigla = sigla;
  }

}