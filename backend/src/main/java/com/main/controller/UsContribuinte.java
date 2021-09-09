package com.main.controller;

import java.sql.SQLException;

import com.main.bo.pessoa.Contribuinte;
import com.main.model.database.DbConnection;

public class UsContribuinte {
  public int Cadastrar(Contribuinte contribuinte) {
    Integer res = 404;
    // contribuinte.
    try{
      DbConnection connection = new DbConnection("root", "123");
      // DbEndereco conEndereco = new DbEndereco(connection);
      // Integer resEndereco = conEndereco.insert(contribuinte.getEnderecoResidencial().getEndereco());
    } catch (SQLException sqlErr) {
      sqlErr.printStackTrace();
    }
    return res;
  }
  
}
