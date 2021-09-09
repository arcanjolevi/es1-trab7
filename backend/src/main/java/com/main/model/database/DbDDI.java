package com.main.model.database;

import java.sql.ResultSet;

public class DbDDI {
    private DbConnection connection;

    public DbDDI(DbConnection connection) {
        this.connection = connection;
    }

    public String insert(String ddi) {
        try {
            this.connection.startTransition();
            String names[] = new String[] { "DDI" };
            String values[] = new String[] { ddi };
            this.connection.insert("DDIs", names, values);
            String res = this.get(ddi);
            this.connection.commit();
            return res;
        } catch (Exception e) {
            try {
                e.printStackTrace();
                this.connection.rollback();
                System.out.println("Inserção do ddi revertido no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public String get(String ddi) throws Exception {
        String sql = "SELECT * FROM DDIs WHERE DDI='" + ddi + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            return res.getString("DDI");
        }
        throw new Exception("DDI nao encontrado.");
    }

}
