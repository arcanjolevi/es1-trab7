package com.main.controller;

import java.sql.SQLException;

import com.main.bo.pessoa.Contribuinte;
import com.main.bo.pessoa.Empresa;
import com.main.bo.pessoa.Rendimento;
import com.main.model.database.DbConnection;
import com.main.model.database.DbContribuinte;
import com.main.model.database.DbEmpresa;
import com.main.model.database.DbRendimento;

public class UsRendimento {
  private DbConnection connection;
  private DbRendimento conRendimento;
  private DbContribuinte conContribuinte;
  private DbEmpresa conEmpresa;

  public UsRendimento() {
    try {
      this.connection = new DbConnection("root", "123");
      this.conRendimento = new DbRendimento(this.connection);
      this.conContribuinte = new DbContribuinte(this.connection);
      this.conEmpresa = new DbEmpresa(this.connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int Cadastrar(Rendimento rendimento, String cnpj, String cpf) throws Exception {
    if (rendimento.getInss() == null)
      throw new Error("Falta de dados na estrutura, Inss.");
    if (rendimento.getIrrf() == null)
      throw new Error("Falta de dados na estrutura, Irrf.");
    if (rendimento.getValor() == null)
      throw new Error("Falta de dados na estrutura, Valor.");

    Empresa empresa = this.conEmpresa.get(cnpj);
    rendimento.setEmpresa(empresa);
    this.conContribuinte.get(cpf);

    return this.conRendimento.insert(rendimento, cpf);
  }

  public Rendimento Consultar(Integer id) throws SQLException, Exception {
    try {
      return this.conRendimento.get(id);
    } catch (SQLException sqlErr) {
      throw sqlErr;
    } catch (Exception Err) {
      throw Err;
    }
  }
}
