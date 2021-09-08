package src;

import src.bo.endereco.Uf;
import src.model.DbConnection;
import src.model.DbUfs;

public class Main {
    public static void main(String args[]) {
        try {
            DbConnection connection = new DbConnection("user", "123");
            Uf u = new Uf("nome", "sigla");
            DbUfs duf = new DbUfs(connection);
            duf.insert(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
