package src.model;

import src.bo.comunicacao.Telefone;

public class DbFone {
    private DbConnection connection;
    private DbDDD dbDDD;
    private DbDDI dbDDI;

    public DbFone(DbConnection connection) {
        this.connection = connection;
        this.dbDDD = new DbDDD(connection);
        this.dbDDI = new DbDDI(connection);
    }

    public Integer insert(Telefone telefone) {
        try {
            this.connection.startTransition();
            String DDD = null, DDI = null;

            try {
                DDD = this.dbDDD.get(telefone.getDdd());
            } catch (Exception e) {
                DDD = this.dbDDD.insert(telefone.getDdd());
            }

            try {
                DDI = this.dbDDI.get(telefone.getDdi());
            } catch (Exception e) {
                DDI = this.dbDDI.insert(telefone.getDdi());
            }

            String names[] = new String[] { "numero", "DDDs_DDD", "DDIs_DDI" };
            String values[] = new String[] { telefone.getNumero(), DDD, DDI };
            Integer res = this.connection.insert("Fones", names, values).getInt(1);
            this.connection.commit();
            return res;
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção do telefone revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }
}
