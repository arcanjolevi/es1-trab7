package src;

import src.bo.endereco.*;
import src.model.DbConnection;
import src.model.*;

public class Main {
    public static void main(String args[]) {
        try {
            DbConnection connection = new DbConnection("user", "123");
            Uf u = new Uf("nome", "sigla");
            Cidade c = new Cidade("cidade", u);
            DbCidade db = new DbCidade(connection);
            // db.insert(c);
            // db.get(2);
            db.remove(7);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
