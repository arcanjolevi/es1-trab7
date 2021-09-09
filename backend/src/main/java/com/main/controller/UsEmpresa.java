package com.main.controller;

import java.sql.SQLException;

import com.main.bo.pessoa.Empresa;
import com.main.model.database.DbConnection;

public class UsEmpresa {
  public int Cadastrar(Empresa empresa) {
    Integer res = 404;
    // contribuinte.
    try{
      DbConnection connection = new DbConnection("root", "123");
      // DbEndereco conEndereco = new DbEndereco(connection);
      // Integer resEndereco = conEndereco.insert(empresa.getEnderecoResidencial().getEndereco());
    } catch (SQLException sqlErr) {
      sqlErr.printStackTrace();
    }
    return res;
  }
}
