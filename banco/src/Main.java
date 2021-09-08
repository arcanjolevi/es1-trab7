package src;

import src.bo.endereco.*;
import src.model.DbConnection;
import src.model.*;

public class Main {
    public static void main(String args[]) {
        try {
            DbConnection connection = new DbConnection("user", "123");
            Uf u = new Uf("nome7", "sigla7");
            Cidade c = new Cidade("cidade7", u);
            DbCidade db = new DbCidade(connection);
            Integer id = db.insert(c);
            System.out.println("Get: " + db.get(id).getNome());
            db.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
