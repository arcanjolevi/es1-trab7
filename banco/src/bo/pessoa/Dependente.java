package src.bo.pessoa;

import java.util.ArrayList;

import src.bo.comunicacao.Email;
import src.bo.comunicacao.Telefone;
import src.bo.endereco.EnderecoEspecifico;

public class Dependente extends PessoaFisica {
  private TipoDependente tipoDependente;

  public Dependente(String nome, ArrayList<Telefone> telefones, ArrayList<Email> emails,
      EnderecoEspecifico enderecoResidencial, String sobrenome, String nomeSocial, String cpf, String rg,
      Character sexo, TipoDependente tipoDependente) {
    super(nome, telefones, emails, enderecoResidencial, sobrenome, nomeSocial, cpf, rg, sexo);
    this.tipoDependente = new TipoDependente(tipoDependente);// Composição
  }

  public TipoDependente getTipoDependente() {
    return tipoDependente;
  }

  public void setTipoDependente(TipoDependente tipoDependente) {
    this.tipoDependente = tipoDependente;
  }

}