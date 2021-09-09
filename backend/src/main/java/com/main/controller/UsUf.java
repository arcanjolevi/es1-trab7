package com.main.controller;

import java.sql.SQLException;

import com.main.bo.endereco.Uf;
import com.main.model.database.DbConnection;
import com.main.model.database.DbUfs;

public class UsUf {
  public boolean cadastrar(Uf uf) {
    if (uf.getNome() != null && uf.getSigla().length() == 2) {
      try {
        DbConnection connection = new DbConnection("root", "123");
        DbUfs dbUf = new DbUfs(connection);
        dbUf.insert(uf);

        return true;
      } catch (SQLException sqlErr) {
        sqlErr.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return false;
  }

}
