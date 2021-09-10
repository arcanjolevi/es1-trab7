package com.main.model.database;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.main.bo.comunicacao.Telefone;

public class DbFoneDependente {
    private DbConnection connection;
    private DbFone dbFone;

    public DbFoneDependente(DbConnection connection) {
        this.connection = connection;
        this.dbFone = new DbFone(connection);
    }

    public void insert(Telefone telefone, Integer idDependente) {
        try {
            this.connection.startTransition();
            Integer idFone = this.dbFone.getId(telefone);
            String names[] = new String[] { "Fones_idFone", "Dependentes_idDependentes" };
            String values[] = new String[] { idFone.toString(), idDependente.toString() };
            this.connection.insert("Fone_Dependente", names, values);
            this.connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                this.connection.rollback();
                System.out.println("Inserção do Fone_Dependente revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }

    public ArrayList<Telefone> get(Integer idEmpresa) throws Exception {
        String sql = "SELECT * FROM Fone_Dependente WHERE Dependentes_idDependentes='" + idEmpresa + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        ArrayList<Telefone> telefones = new ArrayList<>();
        while (res.next()) {
            Integer idFone = Integer.parseInt(res.getString("Fones_idFone"));
            telefones.add(this.dbFone.get(idFone));
        }
        return telefones;
    }

    public Integer getId(Integer idDependente, Integer idFone) throws Exception {
        String sql = "SELECT * FROM Fone_Dependente WHERE Dependentes_idDependentes='" + idDependente
                + "' AND Fones_idFone = '" + idFone + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);

        if (res.next()) {
            return res.getInt(1);
        }
        throw new Exception("Relacao fone e dependente nao encontrada");
    }

    public void remove(Integer idFone, Integer idEmpresa) {
        try {
            this.connection.startTransition();
            String sql = "DELETE FROM Fone_Dependente WHERE Dependentes_idDependentes='" + idEmpresa
                    + "' AND Fones_idFone='" + idFone + "';";
            this.connection.execute(sql);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Remoção do fone_dependente revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }
}
