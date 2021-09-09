package src.model;

import src.bo.comunicacao.Telefone;

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
                System.out.println("Inserção do email revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }
}
