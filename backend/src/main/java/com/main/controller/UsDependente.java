package com.main.controller;

import java.sql.SQLException;

import com.main.bo.pessoa.Dependente;
import com.main.model.database.DbConnection;

public class UsDependente {
  public int Cadastrar(Dependente dependente) {
    Integer res = 404;
    // contribuinte.
    try{
      DbConnection connection = new DbConnection("root", "123");
      // DbEndereco conEndereco = new DbEndereco(connection);
      // Integer resEndereco = conEndereco.insert(dependente.getEnderecoResidencial().getEndereco());
    } catch (SQLException sqlErr) {
      sqlErr.printStackTrace();
    }
    return res;
  }
}
