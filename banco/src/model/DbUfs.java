package src.model;

import src.bo.endereco.Uf;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUfs {
    private DbConnection connection;

    public DbUfs(DbConnection connection) {
        this.connection = connection;
    }

    public String insert(Uf uf) throws Error, SQLException {
        this.connection.insert("Ufs", new String[] { "siglaUf", "nomeUf" },
                new String[] { uf.getSigla(), uf.getNome() });
        Uf aux = this.get(uf.getSigla());
        return aux != null ? aux.getSigla() : null;
    }

    public Uf get(String sigla) {
        try {
            ResultSet res = this.connection.createStatement()
                    .executeQuery("SELECT * FROM Ufs WHERE siglaUf='" + sigla + "';");
            res.next();
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
                this.connection.execute("DELETE FROM Ufs WHERE siglaUf='" + aux.getSigla() + "';");
            } else {
                throw new Error("Uf nao encontrada no banco.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}