package src.bo.pessoa;

import java.util.ArrayList;

import src.bo.comunicacao.Email;
import src.bo.comunicacao.Telefone;
import src.bo.endereco.EnderecoEspecifico;

public class Empresa extends PessoaJuridica {

  public Empresa(String nome, ArrayList<Telefone> telefones, ArrayList<Email> emails,
      EnderecoEspecifico enderecoResidencial, String cnpj) {
    super(nome, telefones, emails, enderecoResidencial, cnpj);
  }

}