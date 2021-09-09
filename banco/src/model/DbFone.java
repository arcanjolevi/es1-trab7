package src.model;

import java.sql.ResultSet;

import src.bo.comunicacao.Telefone;

public class DbFone {
    private DbConnection connection;
    private DbDDD dbDDD;
    private DbDDI dbDDI;

    public DbFone(DbConnection connection) {
        this.connection = connection;
        this.dbDDD = new DbDDD(connection);
        this.dbDDI = new DbDDI(connection);
    }

    public Integer insert(Telefone telefone) {
        try {
            this.connection.startTransition();
            String DDD = null, DDI = null;

            try {
                DDD = this.dbDDD.get(telefone.getDdd());
            } catch (Exception e) {
                DDD = this.dbDDD.insert(telefone.getDdd());
            }

            try {
                DDI = this.dbDDI.get(telefone.getDdi());
            } catch (Exception e) {
                DDI = this.dbDDI.insert(telefone.getDdi());
            }

            try {
                this.getId(telefone);
                throw new Exception("Telefone já inserido");
            } catch (Exception e) {
                if (e.getMessage().compareTo("Telefone já inserido") == 0) {
                    throw e;
                }
            }
            String names[] = new String[] { "numero", "DDDs_DDD", "DDIs_DDI" };
            String values[] = new String[] { telefone.getNumero(), DDD, DDI };
            Integer res = this.connection.insert("Fones", names, values).getInt(1);
            this.connection.commit();
            return res;
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção do telefone revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public Telefone get(Integer idTelefone) throws Exception {
        String sql = "SELECT * FROM Fones WHERE idFone='" + idTelefone + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            Telefone telefone = new Telefone(res.getString("DDDs_DDD"), res.getString("DDIs_DDI"),
                    res.getString("numero"));
            return telefone;
        }
        throw new Exception("Telefone não encontrado.");
    }

    public Integer getId(Telefone telefone) throws Exception {
        String sql = "SELECT * FROM Fones WHERE numero='" + telefone.getNumero() + "' AND DDDs_DDD='"
                + telefone.getDdd() + "' AND DDIs_DDI='" + telefone.getDdi() + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            return res.getInt(1);
        }
        throw new Exception("Telefone não encontrado.");
    }

    public void remove(Telefone telefone) {
        try {
            this.connection.startTransition();
            Integer idFone = this.getId(telefone);
            String sql = "DELETE FROM Fones WHERE idFone='" + idFone + "';";
            this.connection.execute(sql);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Remoção do telefone revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }
}
