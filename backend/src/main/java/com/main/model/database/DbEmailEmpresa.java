package com.main.model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.main.bo.comunicacao.Email;

public class DbEmailEmpresa {
    private DbConnection connection;

    public DbEmailEmpresa(DbConnection connection) {
        this.connection = connection;
    }

    public void insert(Email email, Integer idEmpresa) throws SQLException {
        try {
            this.connection.startTransition();
            String names[] = new String[] { "Empresas_idEmpresa", "Email_Empresa" };
            String values[] = new String[] { idEmpresa.toString(), email.getEmail() };
            this.connection.insert("Email_Empresa", names, values);
            this.connection.commit();
        } catch (SQLException e1) {
            throw e1;
        } catch (Exception e) {

            try {
                this.connection.rollback();
                System.out.println("Inserção do email revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }

    public ArrayList<Email> get(Integer idEmpresa) throws Exception {
        String sql = "SELECT * FROM Email_Empresa WHERE Empresas_idEmpresa='" + idEmpresa + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        ArrayList<Email> emails = new ArrayList<>();
        while (res.next()) {
            emails.add(new Email(res.getString("Email_Empresa")));
        }
        return emails;
    }

    public Email get(Email email) throws Exception {
        String sql = "SELECT * FROM Email_Empresa WHERE Email_Empresa='" + email.getEmail() + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            Email em = new Email(res.getString("Email_Empresa"));
            return em;
        }
        throw new Exception("Email não encontrado.");
    }

    public Integer getId(String email, Integer idEmpresa) throws Exception {
        String sql = "SELECT * FROM Email_Empresa WHERE Email_Empresa='" + email + "' AND Empresas_idEmpresa='"
                + idEmpresa + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            return res.getInt(1);
        }
        throw new Exception("Email não encontrado.");
    }

    public void remove(Email email, Integer idEmpresa) {
        try {
            this.connection.startTransition();
            Integer idEmail = this.getId(email.getEmail(), idEmpresa);
            String sql = "DELETE FROM Email_Empresa WHERE idEmailContribuinte='" + idEmail + "';";
            this.connection.execute(sql);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Remoção do email_empresa revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }
}
