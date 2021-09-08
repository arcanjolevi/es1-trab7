package src.bo.pessoa;

import java.util.ArrayList;

import src.bo.comunicacao.Email;
import src.bo.comunicacao.Telefone;
import src.bo.endereco.EnderecoEspecifico;

public class PessoaJuridica extends Pessoa {
  private String cnpj;

  public PessoaJuridica(String nome, ArrayList<Telefone> telefones, ArrayList<Email> emails,
      EnderecoEspecifico enderecoResidencial, String cnpj) {
    super(nome, telefones, emails, enderecoResidencial);
    this.cnpj = cnpj;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

}