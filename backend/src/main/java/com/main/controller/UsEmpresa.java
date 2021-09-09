package com.main.controller;

import java.sql.SQLException;

import com.main.bo.pessoa.Empresa;
import com.main.model.database.DbConnection;
import com.main.model.database.DbEmpresa;

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

  public Empresa Consultar(String cnpj) throws SQLException, Exception {
    try {
      return conEmpresa.get(cnpj);
    } catch (SQLException sqlErr) {
      throw sqlErr;
    } catch (Exception Err) {
      throw Err;
    }
  }
}
