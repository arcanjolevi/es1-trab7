package com.main.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.main.bo.pessoa.Empresa;
import com.main.model.database.DbConnection;

public class UsEmpresa {
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
    try {
      DbConnection connection = new DbConnection("root", "123");
      // DbEndereco conEndereco = new DbEndereco(connection);
      // Integer resEndereco =
      // conEndereco.insert(contribuinte.getEnderecoResidencial().getEndereco());
      return 1;
    } catch (SQLException sqlErr) {
      sqlErr.printStackTrace();
      throw new Error("Erro ao comunicar com o banco.");
    }
  }

  public ArrayList<Empresa> Consultar() {
    try {
      DbConnection connection = new DbConnection("root", "123");
      // DbEmpresa conEmpresa = new DbEmpresa(connection);
      ArrayList<Empresa> resEmpresa;// = conEmpresa.get();
      connection.closeConnection();
      return resEmpresa;
    } catch (SQLException sqlErr) {
      sqlErr.printStackTrace();
      throw new Error("Erro ao comunicar com o banco.");
    }
  }
}
