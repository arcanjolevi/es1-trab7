package com.main.model.database;

import java.sql.ResultSet;

import com.main.bo.endereco.Logradouro;
import com.main.bo.endereco.TipoLogradouro;

public class DbLogradouro {
    private DbConnection connection;
    private DbTipoLogradouro dTL;

    public DbLogradouro(DbConnection connection) {
        this.connection = connection;
        this.dTL = new DbTipoLogradouro(connection);
    }

    public Integer insert(Logradouro logradouro) {
        try {
            this.connection.startTransition();
            String siglaTipoLogradouro;
            try {
                siglaTipoLogradouro = this.dTL.insert(logradouro.getTipoLogradouro());
            } catch (Exception e) {
                if (e.getMessage().compareTo("Ja inserido") == 0) {
                    siglaTipoLogradouro = this.dTL.get(logradouro.getTipoLogradouro().getSigla()).getSigla();
                } else {
                    throw new Exception("Não foi possível inserir ou buscar o TipoLogradouro no banco.");
                }
            }
            String names[] = new String[] { "nomeLogradouro", "TipoLogradouro_siglaLogradouro" };
            String values[] = new String[] { logradouro.getNome(), siglaTipoLogradouro };
            Integer res = this.connection.insert("Logradouros", names, values).getInt(1);
            this.connection.commit();
            return res;
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção do logradouro revertido no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public Logradouro get(Integer idLogradouro) throws Exception {
        String sql = "SELECT * FROM Logradouros WHERE idLogradouro='" + idLogradouro + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            TipoLogradouro tipoLogradouro = new TipoLogradouro(
                    this.dTL.get(res.getString("TipoLogradouro_siglaLogradouro")));
            Logradouro logradouro = new Logradouro(res.getString("nomeLogradouro"), tipoLogradouro);
            return logradouro;
        }
        throw new Exception("TipoLogradouro não encontrado.");
    }

    public Integer get(Logradouro logradouro) throws Exception {
        String sql = "SELECT * FROM Logradouros WHERE nomeLogradouro='" + logradouro.getNome()
                + "' AND TipoLogradouro_siglaLogradouro='" + logradouro.getTipoLogradouro().getSigla() + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            return res.getInt(1);
        }
        throw new Exception("TipoLogradouro não encontrado.");
    }

    public void remove(Integer idLogradouro) {
        try {
            this.connection.startTransition();
            String sql = "DELETE FROM Logradouros WHERE idLogradouro='" + idLogradouro + "';";
            this.connection.execute(sql);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Remoção do logradouro revertido no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }
}
