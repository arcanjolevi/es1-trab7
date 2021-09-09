package com.main.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.main.bo.pessoa.Empresa;
import com.main.model.database.DbConnection;
import com.main.model.database.DbEmpresa;
import com.main.model.database.DbEndereco;

public class UsEmpresa {
  private DbConnection connection;
  private DbEmpresa conEmpresa;

  public UsEmpresa() {
    try {
      this.connection = new DbConnection("root", "123");
      this.conEmpresa = new DbEmpresa(this.connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int Cadastrar(Empresa empresa) throws Exception {
    if (empresa.getCnpj() == null || empresa.getNome() == null || empresa.getEnderecoResidencial().getNroCasa() == null
        || empresa.getEnderecoResidencial().getEndereco().getCep() == null
        || empresa.getEnderecoResidencial().getEndereco().getBairro().getNome() == null
        || empresa.getEnderecoResidencial().getEndereco().getCidade().getNome() == null
        || empresa.getEnderecoResidencial().getEndereco().getCidade().getUf().getNome() == null
        || empresa.getEnderecoResidencial().getEndereco().getLogradouro().getNome() == null
        || empresa.getEmails().isEmpty() || empresa.getTelefones().isEmpty()) {
      throw new Error("Falta de dados na estrutura.");
    }
    return this.conEmpresa.insert(empresa);
  }

  public Empresa Consultar(String cnpj) {
    try {
      return conEmpresa.get(cnpj);
    } catch (SQLException sqlErr) {
      sqlErr.printStackTrace();
      throw new Error("Erro ao comunicar com o banco.");
    } catch (Exception Err) {
      Err.printStackTrace();
      throw new Error("Erro ao comunicar com o banco.");
    }
  }
}
