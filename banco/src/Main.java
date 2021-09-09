package src;

import src.bo.endereco.*;
import src.model.DbConnection;
import src.model.*;

public class Main {
    public static void main(String args[]) {
        try {
            DbConnection connection = new DbConnection("user", "123");
            Uf u = new Uf("Parana", "PR-5");
            Cidade c = new Cidade("Foz do Iguacu", u);
            TipoLogradouro t = new TipoLogradouro("logradouro", "SGL");
            DbCidade db = new DbCidade(connection);
            Integer id = db.insert(c);
            // System.out.println("Get: " + db.get(id).getNome());
            // db.remove(28);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
