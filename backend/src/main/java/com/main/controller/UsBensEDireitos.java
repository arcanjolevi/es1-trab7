package com.main.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.main.bo.bensedireitos.BensEDireitos;
import com.main.model.database.DbConnection;
import com.main.model.database.DbContribuinte;
import com.main.model.database.DbDireitosBens;

public class UsBensEDireitos {
  private DbConnection connection;
  private DbDireitosBens conBensEDireitos;
  private DbContribuinte conContribuinte;

  public UsBensEDireitos() {
    try {
      this.connection = new DbConnection("root", "123");
      this.conBensEDireitos = new DbDireitosBens(this.connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int Cadastrar(BensEDireitos bensEDireitos, String cpf) throws Exception {
    if (bensEDireitos.getValor() == null)
      throw new Exception("Falta de dados na estrutura, Valor.");
    if (bensEDireitos.getTipoBem().getNome() == null)
      throw new Exception("Falta de dados na estrutura, Tipo Bem.");
    if (bensEDireitos.getTipoBem().getDescricao() == null)
      throw new Exception("Falta de dados na estrutura, Descrição.");

    this.conContribuinte.get(cpf);

    return this.conBensEDireitos.insert(bensEDireitos, cpf);
  }

  public BensEDireitos Consultar(Integer id) throws SQLException, Exception {
    try {
      return conBensEDireitos.get(id);
    } catch (SQLException sqlErr) {
      throw sqlErr;
    } catch (Exception Err) {
      throw Err;
    }
  }

  public ArrayList<BensEDireitos> Consultar(String cpf) throws SQLException, Exception {
    try {
      return conBensEDireitos.get(cpf);
    } catch (SQLException sqlErr) {
      throw sqlErr;
    } catch (Exception Err) {
      throw Err;
    }
  }
}
