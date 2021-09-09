package com.main.model.database;

import com.main.bo.endereco.Uf;
import java.sql.ResultSet;

public class DbUfs {
    private DbConnection connection;

    public DbUfs(DbConnection connection) {
        this.connection = connection;
    }

    public void insert(Uf uf) {
        this.connection.insert("Ufs", new String[] { uf.getSigla(), uf.getNome() });
    }

    public Uf get(String sigla) {
        try {
            ResultSet res = this.connection.createStatement()
                    .executeQuery("SELECT siglaUf FROM Ufs WHERE siglaUf=" + sigla + ";");
            Uf uf = new Uf(res.getString("nomeUf"), res.getString("siglaUf"));
            return uf;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void remove(Uf uf) {
        try {
            Uf aux = this.get(uf.getSigla());
            if (aux != null) {
                this.connection.execute("DELETE FROM Ufs WHERE siglaUf=" + aux.getSigla() + ";");
            } else {
                throw new Error("Uf nao encontrada no banco.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
