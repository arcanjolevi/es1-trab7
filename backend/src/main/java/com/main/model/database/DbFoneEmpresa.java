package com.main.model.database;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.main.bo.comunicacao.Telefone;

public class DbFoneEmpresa {
    private DbConnection connection;
    private DbFone dbFone;

    public DbFoneEmpresa(DbConnection connection) {
        this.connection = connection;
        this.dbFone = new DbFone(connection);
    }

    public void insert(Telefone telefone, Integer idEmpresa) {
        try {
            this.connection.startTransition();
            Integer idFone = this.dbFone.getId(telefone);
            String names[] = new String[] { "Fones_idFone", "Empresas_idEmpresa" };
            String values[] = new String[] { idFone.toString(), idEmpresa.toString() };
            this.connection.insert("Fone_Empresa", names, values).getInt(1);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção do Fone_Empresa revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }

    public ArrayList<Telefone> get(Integer idEmpresa) throws Exception {
        String sql = "SELECT * FROM Fone_Empresa WHERE Empresas_idEmpresa='" + idEmpresa + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        ArrayList<Telefone> telefones = new ArrayList<>();
        while (res.next()) {
            Integer idFone = Integer.parseInt(res.getString("Fones_idFone"));
            telefones.add(this.dbFone.get(idFone));
        }
        return telefones;
    }

    public void remove(Integer idFone, Integer idEmpresa) {
        try {
            this.connection.startTransition();
            String sql = "DELETE FROM Fone_Empresa WHERE Empresas_idEmpresa='" + idEmpresa + "' AND Fones_idFone='"
                    + idFone + "';";
            this.connection.execute(sql);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Remoção do fone_empresa revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }
}
