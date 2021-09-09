package com.main.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.main.bo.pessoa.Dependente;
import com.main.model.database.DbConnection;

public class UsDependente {
  public int Cadastrar(Dependente dependente) throws Exception {
    if (dependente.getCpf() == null || dependente.getNome() == null || dependente.getNomeSocial() == null
        || dependente.getSobrenome() == null || dependente.getSexo() == null || dependente.getRg() == null
        || dependente.getEnderecoResidencial().getNroCasa() == null
        || dependente.getEnderecoResidencial().getEndereco().getCep() == null
        || dependente.getEnderecoResidencial().getEndereco().getBairro().getNome() == null
        || dependente.getEnderecoResidencial().getEndereco().getCidade().getNome() == null
        || dependente.getEnderecoResidencial().getEndereco().getCidade().getUf().getNome() == null
        || dependente.getEnderecoResidencial().getEndereco().getLogradouro().getNome() == null
        || dependente.getTelefones().isEmpty() || dependente.getEmails().isEmpty()) {
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

  // public ArrayList<Dependente> Consultar() {

  // }
}
