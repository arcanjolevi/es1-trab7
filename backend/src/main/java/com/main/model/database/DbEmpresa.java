package com.main.model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.main.bo.comunicacao.Email;
import com.main.bo.comunicacao.Telefone;
import com.main.bo.endereco.Endereco;
import com.main.bo.endereco.EnderecoEspecifico;
import com.main.bo.pessoa.Empresa;

public class DbEmpresa {
    private DbConnection connection;
    private DbEndereco dbEndereco;
    private DbFoneEmpresa foneEmpresa;
    private DbEmailEmpresa emailEmpresa;
    private DbFone fone;

    public DbEmpresa(DbConnection connection) {
        this.connection = connection;
        this.dbEndereco = new DbEndereco(connection);
        this.foneEmpresa = new DbFoneEmpresa(connection);
        this.emailEmpresa = new DbEmailEmpresa(connection);
        this.fone = new DbFone(connection);
    }

    public Integer insert(Empresa empresa) throws Exception {

        try {
            this.connection.startTransition();

            Integer idEndereco;
            try {

                idEndereco = this.dbEndereco.getId(empresa.getEnderecoResidencial().getEndereco().getCep());
            } catch (Exception e) {
                e.printStackTrace();
                idEndereco = this.dbEndereco.insert(empresa.getEnderecoResidencial().getEndereco());
            }

            String names[] = new String[] { "nomeEmpresa", "cnpjEmpresa", "numeroEnderecoEmpresa",
                    "Endereco_idEndereco", "complementoEmpresa" };
            String values[] = new String[] { empresa.getNome(), empresa.getCnpj(),
                    empresa.getEnderecoResidencial().getNroCasa().toString(), idEndereco.toString(),
                    empresa.getEnderecoResidencial().getComplemento() };
            Integer aux = this.connection.insert("Empresas", names, values).getInt(1);

            for (Telefone t : empresa.getTelefones()) {
                Integer telAux;
                try {
                    telAux = this.fone.getId(t);
                } catch (Exception e) {
                    telAux = this.fone.insert(t);
                }

                try {
                    this.foneEmpresa.getId(aux, telAux);
                } catch (Exception e) {
                    this.foneEmpresa.insert(t, aux);
                }
            }

            for (Email e : empresa.getEmails()) {

                try {
                    this.emailEmpresa.getId(e.getEmail(), aux);
                } catch (Exception exc) {
                    this.emailEmpresa.insert(e, aux);
                }
            }

            this.connection.commit();
            return aux;
        } catch (SQLIntegrityConstraintViolationException e1) {
            throw new Exception("Ja inserido");
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção de Empresa revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public Empresa get(String cnpj) throws Exception, SQLException {
        String sql = "SELECT * FROM Empresas WHERE cnpjEmpresa='" + cnpj + "';";

        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            Endereco end = this.dbEndereco.get(Integer.parseInt(res.getString("Endereco_idEndereco")));

            EnderecoEspecifico enderecoResidencial = new EnderecoEspecifico(
                    Integer.parseInt(res.getString("numeroEnderecoEmpresa")), res.getString("complementoEmpresa"), end);

            ArrayList<Telefone> telefones = this.foneEmpresa.get(Integer.parseInt(res.getString("idEmpresa")));
            ArrayList<Email> emails = this.emailEmpresa.get(Integer.parseInt(res.getString("idEmpresa")));
            Empresa empresa = new Empresa(res.getString("nomeEmpresa"), telefones, emails, enderecoResidencial,
                    res.getString("cnpjEmpresa"));

            return empresa;
        }
        throw new Exception("Uf não encontrada.");
    }
}
