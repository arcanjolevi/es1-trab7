package com.main.model.database;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.main.bo.comunicacao.Email;

public class DbEmailContribuinte {
    private DbConnection connection;

    public DbEmailContribuinte(DbConnection connection) {
        this.connection = connection;
    }

    public Integer insert(Email email, Integer idContribuinte) {
        try {
            this.connection.startTransition();
            String names[] = new String[] { "Contribuinte_idContribuinte", "Email_Contribuinte" };
            String values[] = new String[] { idContribuinte.toString(), email.getEmail() };
            Integer res = this.connection.insert("Email_Contribuinte", names, values).getInt(1);
            this.connection.commit();
            return res;
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção do email revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public ArrayList<Email> get(Integer idContribuinte) throws Exception {
        String sql = "SELECT * FROM Email_Contribuinte WHERE Contribuinte_idContribuinte='" + idContribuinte + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        ArrayList<Email> emails = new ArrayList<>();
        while (res.next()) {
            emails.add(new Email(res.getString("Email_Contribuinte")));
        }
        return emails;
    }

    public Email get(Email email) throws Exception {
        String sql = "SELECT * FROM Email_Contribuinte WHERE Email_Contribuinte='" + email.getEmail() + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            Email em = new Email(res.getString("Email_Contribuinte"));
            return em;
        }
        throw new Exception("Email não encontrado.");
    }

    public Integer getId(String email, Integer idContribuinte) throws Exception {
        String sql = "SELECT * FROM Email_Contribuinte WHERE Email_Contribuinte='" + email
                + "' AND Contribuinte_idContribuinte='" + idContribuinte + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            return res.getInt(1);
        }
        throw new Exception("Email não encontrado.");
    }

    public void remove(Email email, Integer idContribuinte) {
        try {
            this.connection.startTransition();
            Integer idEmail = this.getId(email.getEmail(), idContribuinte);
            String sql = "DELETE FROM Email_Contribuinte WHERE idEmailContribuinte='" + idEmail + "';";
            this.connection.execute(sql);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Remoção do email_contribuinte revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }
}
