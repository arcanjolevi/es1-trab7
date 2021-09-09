package com.main.model.database;

import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import com.main.bo.endereco.Bairro;

public class DbBairro {
    private DbConnection connection;

    public DbBairro(DbConnection connection) {
        this.connection = connection;
    }

    public Integer insert(Bairro bairro) throws Exception {
        try {
            this.connection.startTransition();
            String names[] = new String[] { "nomeBairro" };
            String values[] = new String[] { bairro.getNome() };
            Integer res = this.connection.insert("Bairros", names, values).getInt(1);
            this.connection.commit();
            return res;
        } catch (SQLIntegrityConstraintViolationException e1) {
            throw new Exception("Ja inserido");
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção de Bairro revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public Bairro get(Integer idBairro) throws Exception {
        String sql = "SELECT * FROM Bairros WHERE idBairro='" + idBairro + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            Bairro bairro = new Bairro(res.getString("nomeBairro"));
            return bairro;
        }
        throw new Exception("Bairro não encontrado.");
    }

    public Integer get(Bairro bairro) throws Exception {
        String sql = "SELECT * FROM Bairros WHERE nomeBairro='" + bairro.getNome() + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            return res.getInt(1);
        }
        throw new Exception("Bairro não encontrado.");
    }

    public void remove(Integer idBairro) {
        try {
            this.connection.startTransition();
            String sql = "DELETE FROM Bairros WHERE idBairro='" + idBairro + "';";
            this.connection.execute(sql);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Remoção do bairro revertido no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }
}
