package com.main.model.database;

import com.main.bo.pessoa.Empresa;

public class DbEmpresa {
    private DbConnection connection;

    public DbEmpresa(DbConnection connection) {
        this.connection = connection;
    }

    public void insert(Empresa empresa) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
