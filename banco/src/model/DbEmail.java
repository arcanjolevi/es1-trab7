package src.model;

import java.sql.ResultSet;
import java.util.ArrayList;

import src.bo.comunicacao.Email;

public class DbEmail {
    private DbConnection connection;

    public DbEmail(DbConnection connection) {
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
        if (res.next()) {
            emails.add(new Email(res.getString("Email_Contribuinte")));
        }
        return emails;
    }

    public ArrayList<Email> get(Email email) throws Exception {
        String sql = "SELECT * FROM Email_Contribuinte WHERE Email_Contribuinte='" + email.getEmail() + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        ArrayList<Email> emails = new ArrayList<>();
        if (res.next()) {
            emails.add(new Email(res.getString("Email_Contribuinte")));
        }
        return emails;
    }
}
