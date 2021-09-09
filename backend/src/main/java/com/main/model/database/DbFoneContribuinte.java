package com.main.model.database;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.main.bo.comunicacao.Telefone;

public class DbFoneContribuinte {
    private DbConnection connection;
    private DbFone dbFone;

    public DbFoneContribuinte(DbConnection connection) {
        this.connection = connection;
        this.dbFone = new DbFone(connection);
    }

    public void insert(Telefone telefone, Integer idContribuinte) {
        try {
            this.connection.startTransition();
            Integer idFone = this.dbFone.getId(telefone);
            String names[] = new String[] { "Contribuinte_idContribuinte", "Fones_idFone" };
            String values[] = new String[] { idContribuinte.toString(), idFone.toString() };
            this.connection.insert("Fone_Contribuinte", names, values).getInt(1);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção do Fone_Contribuinte revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }

    public ArrayList<Telefone> get(Integer idContribuinte) throws Exception {
        String sql = "SELECT * FROM Fone_Contribuinte WHERE Contribuinte_idContribuinte='" + idContribuinte + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        ArrayList<Telefone> telefones = new ArrayList<>();
        while (res.next()) {
            Integer idFone = Integer.parseInt(res.getString("Fones_idFone"));
            telefones.add(this.dbFone.get(idFone));
        }
        return telefones;
    }

    public Integer getId(Integer idContribuinte, Integer idFone) throws Exception {
        String sql = "SELECT * FROM Fone_Contribuinte WHERE Contribuinte_idContribuinte='" + idContribuinte
                + "' AND Fones_idFone = '" + idFone + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);

        if (res.next()) {
            return res.getInt(1);
        }
        throw new Exception("Relacao fone e contribuinte nao encontrada");
    }

    public void remove(Integer idFone, Integer idContribuinte) {
        try {
            this.connection.startTransition();
            String sql = "DELETE FROM Fone_Contribuinte WHERE Contribuinte_idContribuinte='" + idContribuinte
                    + "' AND Fones_idFone='" + idFone + "';";
            this.connection.execute(sql);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Remoção do fone_contribuinte revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }
}
