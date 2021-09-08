package com.main.model.database;

import com.main.bo.endereco.Cidade;
import java.sql.ResultSet;
import com.main.model.database.DbUfs;

public class DbCidade {
    private DbConnection connection;

    public DbCidade(DbConnection connection) {
        this.connection = connection;
    }

    public Integer insert(Cidade cidade) {
        try {
            DbUfs uf = new DbUfs(connection);
            String sigla = uf.insert(cidade.getUf());
            if (sigla != null) {
                this.connection.insert("Cidades", new String[] { cidade.getNome(), sigla });
            } else {
                throw new Error("Nao foi possivel inserir a Uf.");
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void get(Integer idCidade) {
        try {
            ResultSet res = this.connection.createStatement()
                    .executeQuery("SELECT * FROM Cidades WHERE idCidade=" + idCidade + ";");
            // Cidade cidade = new Cidade(res.getString("nomeCidade"), (Uf)
            // res.getObject("Ufs_siglaUf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
