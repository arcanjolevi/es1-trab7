package src.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import src.bo.endereco.TipoLogradouro;

public class DbTipoLogradouro {
    private DbConnection connection;

    public DbTipoLogradouro(DbConnection connection) {
        this.connection = connection;
    }

    public String insert(TipoLogradouro tipoLogradouro) {
        try {
            this.connection.startTransition();
            String names[] = new String[] { "siglaLogradouro", "nomeTipoLogradouro" };
            String values[] = new String[] { tipoLogradouro.getSigla(), tipoLogradouro.getNome() };
            this.connection.insert("TipoLogradouro", names, values);
            TipoLogradouro aux = this.get(tipoLogradouro.getSigla());
            this.connection.commit();
            return aux.getSigla();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção de TipoLogradouro revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public TipoLogradouro get(String sigla) throws SQLException {
        String sql = "SELECT * FROM TipoLogradouro WHERE siglaLogradouro='" + sigla + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            TipoLogradouro tipo = new TipoLogradouro(res.getString("nomeTipoLogradouro"),
                    res.getString("siglaLogradouro"));
            return tipo;
        }
        throw new Error("TipoLogradouro não encontrado.");
    }
}
