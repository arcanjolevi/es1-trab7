package com.main.model.database;

import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import com.main.bo.endereco.TipoLogradouro;

public class DbTipoLogradouro {
    private DbConnection connection;

    public DbTipoLogradouro(DbConnection connection) {
        this.connection = connection;
    }

    public String insert(TipoLogradouro tipoLogradouro) throws Exception {
        try {
            this.connection.startTransition();
            String names[] = new String[] { "siglaLogradouro", "nomeTipoLogradouro" };
            String values[] = new String[] { tipoLogradouro.getSigla(), tipoLogradouro.getNome() };
            this.connection.insert("TipoLogradouro", names, values);
            TipoLogradouro aux = this.get(tipoLogradouro.getSigla());
            this.connection.commit();
            return aux.getSigla();
        } catch (SQLIntegrityConstraintViolationException e1) {
            throw new Exception("Ja inserido");
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção de TipoLogradouro revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public TipoLogradouro get(String sigla) throws Exception {
        String sql = "SELECT * FROM TipoLogradouro WHERE siglaLogradouro='" + sigla + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            TipoLogradouro tipo = new TipoLogradouro(res.getString("nomeTipoLogradouro"),
                    res.getString("siglaLogradouro"));
            return tipo;
        }
        throw new Exception("TipoLogradouro não encontrado.");
    }

    public void remove(String sigla) {
        try {
            this.connection.startTransition();
            TipoLogradouro aux = this.get(sigla);
            String sql = "DELETE FROM TipoLogradouro WHERE siglaLogradouro='" + aux.getSigla() + "';";
            this.connection.execute(sql);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Remoção de TipoLogradouro revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }
}
