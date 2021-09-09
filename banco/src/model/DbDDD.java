package src.model;

import java.sql.ResultSet;

public class DbDDD {
    private DbConnection connection;

    public DbDDD(DbConnection connection) {
        this.connection = connection;
    }

    public String insert(String ddd) {
        try {
            this.connection.startTransition();
            String names[] = new String[] { "DDD" };
            String values[] = new String[] { ddd };
            this.connection.insert("DDDs", names, values);
            String res = this.get(ddd);
            this.connection.commit();
            return res;
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção do ddd revertido no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public String get(String ddd) throws Exception {
        String sql = "SELECT * FROM DDDs WHERE DDD='" + ddd + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            return res.getString("DDD");
        }
        throw new Exception("Cidade nao encontrada.");
    }

}
