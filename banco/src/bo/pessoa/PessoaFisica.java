package src.bo.pessoa;

import java.util.ArrayList;

import src.bo.comunicacao.Email;
import src.bo.comunicacao.Telefone;
import src.bo.endereco.EnderecoEspecifico;

public class PessoaFisica extends Pessoa {
  private String sobrenome;
  private String nomeSocial;
  private String cpf;
  private String rg;
  private Character sexo;

  public PessoaFisica(String nome, ArrayList<Telefone> telefones, ArrayList<Email> emails,
      EnderecoEspecifico enderecoResidencial, String sobrenome, String nomeSocial, String cpf, String rg,
      Character sexo) {
    super(nome, telefones, emails, enderecoResidencial);
    this.sobrenome = sobrenome;
    this.nomeSocial = nomeSocial;
    this.cpf = cpf;
    this.sexo = sexo;
  }

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  public String getNomeSocial() {
    return nomeSocial;
  }

  public void setNomeSocial(String nomeSocial) {
    this.nomeSocial = nomeSocial;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getRg() {
    return rg;
  }

  public void setRg(String rg) {
    this.rg = rg;
  }

  public Character getSexo() {
    return sexo;
  }

  public void setSexo(Character sexo) {
    this.sexo = sexo;
  }

}