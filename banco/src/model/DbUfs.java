package src.model;

import src.bo.endereco.Uf;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUfs {
    private DbConnection connection;

    public DbUfs(DbConnection connection) {
        this.connection = connection;
    }

    public String insert(Uf uf) {
        try {
            this.connection.startTransition();
            String names[] = new String[] { "siglaUf", "nomeUf" };
            String values[] = new String[] { uf.getSigla(), uf.getNome() };
            this.connection.insert("Ufs", names, values);
            Uf aux = this.get(uf.getSigla());
            this.connection.commit();
            return aux != null ? aux.getSigla() : null;
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção de Uf revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public Uf get(String sigla) throws Error, SQLException {
        String sql = "SELECT * FROM Ufs WHERE siglaUf='" + sigla + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            Uf uf = new Uf(res.getString("nomeUf"), res.getString("siglaUf"));
            return uf;
        }
        throw new Error("Uf não encontrada.");
    }

    public void remove(Uf uf) {
        try {
            this.connection.startTransition();
            Uf aux = this.get(uf.getSigla());
            String sql = "DELETE FROM Ufs WHERE siglaUf='" + aux.getSigla() + "';";
            this.connection.execute(sql);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Remoção de Uf revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }
}
