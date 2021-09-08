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

    public void insert(Cidade cidade) throws Error, SQLException {
        Uf uf = new Uf(cidade.getUf().getNome(), cidade.getUf().getSigla());

        String ufId = this.duf.insert(uf);
        if (ufId != null) {
            this.connection.insert("Cidades", new String[] { "nomeCidade", "Ufs_siglaUf" },
                    new String[] { cidade.getNome(), ufId });
        } else {
            throw new Error("Nao foi possivel inserir o UF no banco.");
        }
    }

    public Cidade get(Integer idCidade) throws Error, SQLException {
        ResultSet res = this.connection.createStatement()
                .executeQuery("SELECT * FROM Cidades WHERE idCidades='" + idCidade + "';");
        if (res.next()) {
            Uf uf = new Uf(this.duf.get((String) res.getObject("Ufs_siglaUf")));
            Cidade cidade = new Cidade(res.getString("nomeCidade"), uf);
            return cidade;
        }
        throw new Error("Cidade nao encontrada");
    }

    public void remove(Integer idCidade) throws Error, SQLException {
        Cidade cidade = this.get(idCidade);
        Uf uf = this.duf.get(cidade.getUf().getSigla());
        this.connection.execute("DELETE FROM Cidades WHERE idCidades='" + idCidade + "';");
        this.duf.remove(uf);
    }
}
