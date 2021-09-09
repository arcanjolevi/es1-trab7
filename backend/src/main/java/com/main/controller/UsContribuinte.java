package com.main.controller;

import java.sql.SQLException;

import com.main.bo.pessoa.Contribuinte;
import com.main.model.database.DbConnection;

public class UsContribuinte {
  private DbConnection connection;
  // private DbContribuinte conContribuinte;

  public UsContribuinte() {
    try {
      this.connection = new DbConnection("root", "123");
      // this.conContribuinte = new DbContribuinte(this.connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int Cadastrar(Contribuinte contribuinte) throws Exception {
    if (contribuinte.getCpf() == null || contribuinte.getNome() == null || contribuinte.getNomeSocial() == null
        || contribuinte.getSobrenome() == null || contribuinte.getSexo() == null || contribuinte.getRg() == null
        || contribuinte.getEnderecoResidencial().getNroCasa() == null
        || contribuinte.getEnderecoResidencial().getEndereco().getCep() == null
        || contribuinte.getEnderecoResidencial().getEndereco().getBairro().getNome() == null
        || contribuinte.getEnderecoResidencial().getEndereco().getCidade().getNome() == null
        || contribuinte.getEnderecoResidencial().getEndereco().getCidade().getUf().getNome() == null
        || contribuinte.getEnderecoResidencial().getEndereco().getLogradouro().getNome() == null
        || contribuinte.getEmails().isEmpty() || contribuinte.getTelefones().isEmpty()) {
      throw new Error("Falta de dados na estrutura.");
    }
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
