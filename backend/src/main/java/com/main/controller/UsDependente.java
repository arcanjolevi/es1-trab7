package com.main.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.main.bo.pessoa.Dependente;
import com.main.model.database.DbConnection;
import com.main.model.database.DbDependente;

public class UsDependente {
  private DbConnection connection;
  private DbDependente conDependente;

  public UsDependente() {
    try {
      this.connection = new DbConnection("root", "123");
      this.conDependente = new DbDependente(this.connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int Cadastrar(Dependente dependente, String cpf) throws Exception {
    if (dependente.getCpf() == null)
      throw new Exception("Falta de dados na estrutura, Cpf.");
    if (dependente.getNome() == null)
      throw new Exception("Falta de dados na estrutura, Nome.");
    if (dependente.getNomeSocial() == null)
      throw new Exception("Falta de dados na estrutura, NomeSocial.");
    if (dependente.getSobrenome() == null)
      throw new Exception("Falta de dados na estrutura, Sobrenome.");
    if (dependente.getSexo() == null)
      throw new Exception("Falta de dados na estrutura, Sexo.");
    if (dependente.getRg() == null)
      throw new Exception("Falta de dados na estrutura, Rg.");
    if (dependente.getEnderecoResidencial().getNroCasa() == null)
      throw new Exception("Falta de dados na estrutura, EnderecoResidencial.");
    if (dependente.getEnderecoResidencial().getEndereco().getCep() == null)
      throw new Exception("Falta de dados na estrutura, EnderecoResidencial.");
    if (dependente.getEnderecoResidencial().getEndereco().getBairro().getNome() == null)
      throw new Exception("Falta de dados na estrutura, EnderecoResidencial.");
    if (dependente.getEnderecoResidencial().getEndereco().getCidade().getNome() == null)
      throw new Exception("Falta de dados na estrutura, EnderecoResidencial.");
    if (dependente.getEnderecoResidencial().getEndereco().getCidade().getUf().getNome() == null)
      throw new Exception("Falta de dados na estrutura, EnderecoResidencial.");
    if (dependente.getEnderecoResidencial().getEndereco().getLogradouro().getNome() == null)
      throw new Exception("Falta de dados na estrutura, EnderecoResidencial.");
    if (dependente.getEmails().isEmpty())
      throw new Exception("Falta de dados na estrutura, Emails.");
    if (dependente.getTelefones().isEmpty())
      throw new Exception("Falta de dados na estrutura, Telefones.");

    return this.conDependente.insert(dependente, cpf);
  }

  public Dependente Consultar(String cpf) throws SQLException, Exception {
    try {
      return conDependente.get(cpf);
    } catch (SQLException sqlErr) {
      throw sqlErr;
    } catch (Exception Err) {
      throw Err;
    }
  }

  public ArrayList<Dependente> ConsultarTudo(String cpf) throws SQLException, Exception {
    try {
      return conDependente.getAll(cpf);
    } catch (SQLException sqlErr) {
      throw sqlErr;
    } catch (Exception Err) {
      throw Err;
    }
  }
}
