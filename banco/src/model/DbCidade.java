package src.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import src.bo.endereco.Cidade;
import src.bo.endereco.Uf;

public class DbCidade {
    private DbConnection connection;
    private DbUfs duf;

    public DbCidade(DbConnection connection) {
        this.connection = connection;
        this.duf = new DbUfs(connection);
    }

    public Integer insert(Cidade cidade) {
        try {
            this.connection.startTransition();
            String idUf = null;
            try {
                idUf = this.duf.insert(cidade.getUf());
            } catch (Exception e) {
                if (e.getMessage().compareTo("Ja inserido") == 0) {
                    idUf = this.duf.get(cidade.getUf().getSigla()).getSigla();
                }
                throw new Exception("Não foi possível inserir ou buscar o UF no banco.");
            }
            String names[] = new String[] { "nomeCidade", "Ufs_siglaUf" };
            String values[] = new String[] { cidade.getNome(), idUf };
            Integer res = this.connection.insert("Cidades", names, values).getInt(1);
            this.connection.commit();
            return res;
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção da cidade revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public Cidade get(Integer idCidade) throws Exception, SQLException {
        String sql = "SELECT * FROM Cidades WHERE idCidades='" + idCidade + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            Uf uf = new Uf(this.duf.get((String) res.getObject("Ufs_siglaUf")));
            Cidade cidade = new Cidade(res.getString("nomeCidade"), uf);
            return cidade;
        }
        throw new Error("Cidade nao encontrada.");
    }

    public Integer get(Cidade cidade) throws Exception, SQLException {
        String sql = "SELECT * FROM Cidades WHERE nomeCidade='" + cidade.getNome() + "' AND Ufs_siglaUf='"
                + cidade.getUf().getSigla() + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            return res.getInt(1);
        }
        throw new Exception("Cidade nao encontrada.");
    }

    public void remove(Integer idCidade) {
        try {
            this.connection.startTransition();
            String sql = "DELETE FROM Cidades WHERE idCidades='" + idCidade + "';";
            this.connection.execute(sql);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Remoção da cidade revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }
}
