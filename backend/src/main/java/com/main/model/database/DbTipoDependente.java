package com.main.model.database;

import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import com.main.bo.pessoa.TipoDependente;

public class DbTipoDependente {
    private DbConnection connection;

    public DbTipoDependente(DbConnection connection) {
        this.connection = connection;
    }

    public Integer insert(TipoDependente tipoDependente) throws Exception {
        try {
            this.connection.startTransition();
            String names[] = new String[] { "nomeTipoDependente" };
            String values[] = new String[] { tipoDependente.getTipoDependente() };
            Integer res = this.connection.insert("TiposDependente", names, values).getInt(1);
            this.connection.commit();
            return res;
        } catch (SQLIntegrityConstraintViolationException e1) {
            throw new Exception("Ja inserido");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                this.connection.rollback();
                System.out.println("Inserção de TipoDependente revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public TipoDependente get(Integer idTipoDependente) throws Exception {
        String sql = "SELECT * FROM TiposDependente WHERE idTipoDependente='" + idTipoDependente + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            TipoDependente tipoDependente = new TipoDependente(res.getString("nomeTipoDependente"));
            return tipoDependente;
        }
        throw new Exception("TipoDependente não encontrado.");
    }

    public Integer getId(TipoDependente tipoDependente) throws Exception {
        String sql = "SELECT * FROM TiposDependente WHERE nomeTipoDependente='" + tipoDependente.getTipoDependente()
                + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            return res.getInt(1);
        }
        throw new Exception("TipoDependente não encontrado.");
    }
}
