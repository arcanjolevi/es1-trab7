package com.main.controller;

import java.sql.SQLException;

import com.main.bo.pessoa.Contribuinte;
import com.main.model.database.DbConnection;

public class UsContribuinte {
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

}
