package src.model;

import java.sql.ResultSet;
import java.util.ArrayList;

import src.bo.comunicacao.Email;

public class DbEmailDependente {
    private DbConnection connection;

    public DbEmailDependente(DbConnection connection) {
        this.connection = connection;
    }

    public Integer insert(Email email, Integer idDependente) {
        try {
            this.connection.startTransition();
            String names[] = new String[] { "Dependentes_idDependentes", "Email_Dependente" };
            String values[] = new String[] { idDependente.toString(), email.getEmail() };
            Integer res = this.connection.insert("Email_Dependente", names, values).getInt(1);
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

    public ArrayList<Email> get(Integer idDependente) throws Exception {
        String sql = "SELECT * FROM Email_Dependente WHERE Dependentes_idDependentes='" + idDependente + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        ArrayList<Email> emails = new ArrayList<>();
        if (res.next()) {
            emails.add(new Email(res.getString("Email_Dependente")));
        }
        return emails;
    }

    public Email get(Email email) throws Exception {
        String sql = "SELECT * FROM Email_Dependente WHERE Email_Dependente='" + email.getEmail() + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            Email em = new Email(res.getString("Email_Dependente"));
            return em;
        }
        throw new Exception("Email não encontrado.");
    }

    public Integer getId(String email, Integer idDependente) throws Exception {
        String sql = "SELECT * FROM Email_Dependente WHERE Email_Dependente='" + email
                + "' AND Dependentes_idDependentes='" + idDependente + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            return res.getInt(1);
        }
        throw new Exception("Email não encontrado.");
    }

    public void remove(Email email, Integer idDependente) {
        try {
            this.connection.startTransition();
            Integer idEmail = this.getId(email.getEmail(), idDependente);
            String sql = "DELETE FROM Email_Dependente WHERE idEmailContribuinte='" + idEmail + "';";
            this.connection.execute(sql);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Remoção do Email_Dependente revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }
}
