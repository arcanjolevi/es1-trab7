package com.main.controller;

import java.sql.SQLException;

import com.main.bo.pessoa.Contribuinte;
import com.main.model.database.DbConnection;
import com.main.model.database.DbContribuinte;

public class UsContribuinte {
  private DbConnection connection;
  private DbContribuinte conContribuinte;

  public UsContribuinte() {
    try {
      this.connection = new DbConnection("root", "123");
      this.conContribuinte = new DbContribuinte(this.connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int Cadastrar(Contribuinte contribuinte) throws Exception {
    if (contribuinte.getCpf() == null)
      throw new Exception("Falta de dados na estrutura, Cpf.");
    if (contribuinte.getNome() == null)
      throw new Exception("Falta de dados na estrutura, Nome.");
    if (contribuinte.getNomeSocial() == null)
      throw new Exception("Falta de dados na estrutura, NomeSocial.");
    if (contribuinte.getSobrenome() == null)
      throw new Exception("Falta de dados na estrutura, Sobrenome.");
    if (contribuinte.getSexo() == null)
      throw new Exception("Falta de dados na estrutura, Sexo.");
    if (contribuinte.getRg() == null)
      throw new Exception("Falta de dados na estrutura, Rg.");
    if (contribuinte.getEnderecoResidencial().getNroCasa() == null)
      throw new Exception("Falta de dados na estrutura, EnderecoResidencial.");
    if (contribuinte.getEnderecoResidencial().getEndereco().getCep() == null)
      throw new Exception("Falta de dados na estrutura, EnderecoResidencial.");
    if (contribuinte.getEnderecoResidencial().getEndereco().getBairro().getNome() == null)
      throw new Exception("Falta de dados na estrutura, EnderecoResidencial.");
    if (contribuinte.getEnderecoResidencial().getEndereco().getCidade().getNome() == null)
      throw new Exception("Falta de dados na estrutura, EnderecoResidencial.");
    if (contribuinte.getEnderecoResidencial().getEndereco().getCidade().getUf().getNome() == null)
      throw new Exception("Falta de dados na estrutura, EnderecoResidencial.");
    if (contribuinte.getEnderecoResidencial().getEndereco().getLogradouro().getNome() == null)
      throw new Exception("Falta de dados na estrutura, EnderecoResidencial.");
    if (contribuinte.getEmails().isEmpty())
      throw new Exception("Falta de dados na estrutura, Emails.");
    if (contribuinte.getTelefones().isEmpty())
      throw new Exception("Falta de dados na estrutura, Telefones.");

    return this.conContribuinte.insert(contribuinte);
  }

  public Contribuinte Consultar(String cpf) throws SQLException, Exception {
    try {
      return conContribuinte.get(cpf);
    } catch (SQLException sqlErr) {
      throw sqlErr;
    } catch (Exception Err) {
      throw Err;
    }
  }

}
