package com.main.model.database;

import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import com.main.bo.bensedireitos.TipoBensEDireitos;

public class DbTipoDireitoBens {
    private DbConnection connection;

    public DbTipoDireitoBens(DbConnection connection) {
        this.connection = connection;
    }

    public Integer insert(TipoBensEDireitos tipo) throws Exception {
        try {
            this.connection.startTransition();
            String names[] = new String[] { "nomeDireitoBem", "descricaoDireitoBem" };
            String values[] = new String[] { tipo.getNome(), tipo.getDescricao() };
            Integer res = this.connection.insert("TipoDireitoBens", names, values).getInt(1);
            this.connection.commit();
            return res;
        } catch (SQLIntegrityConstraintViolationException e1) {
            throw new Exception("Ja inserido");
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção de TipoDireitoBem revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public TipoBensEDireitos get(String nome) throws Exception {
        String sql = "SELECT * FROM TipoDireitoBens WHERE nomeDireitoBem='" + nome + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            TipoBensEDireitos t = new TipoBensEDireitos(res.getString("nomeDireitoBem"),
                    res.getString("descricaoDireitoBem"));
            return t;
        }
        throw new Exception("TipoDireitoBem não encontrado.");
    }

    public TipoBensEDireitos get(Integer id) throws Exception {
        String sql = "SELECT * FROM TipoDireitoBens WHERE idTipoDireitoBem='" + id + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            TipoBensEDireitos t = new TipoBensEDireitos(res.getString("nomeDireitoBem"),
                    res.getString("descricaoDireitoBem"));
            return t;
        }
        throw new Exception("TipoDireitoBem não encontrado.");
    }

    public Integer getId(String nome) throws Exception {
        String sql = "SELECT * FROM TipoDireitoBens WHERE nomeDireitoBem='" + nome + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            return Integer.parseInt(res.getString("idTipoDireitoBem"));
        }
        throw new Exception("TipoDireitoBem não encontrado.");
    }

    public void remove(String nome) {
        try {
            this.connection.startTransition();
            String sql = "DELETE FROM TipoDireitoBens WHERE nomeDireitoBem='" + nome + "';";
            this.connection.execute(sql);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Remoção de TipoDireitoBem revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }
}
