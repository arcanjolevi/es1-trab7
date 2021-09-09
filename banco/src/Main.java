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
            TipoLogradouro t = new TipoLogradouro("logradouro", "SGL-2");
            Logradouro l = new Logradouro("Logradouro da vez", t);
            DbLogradouro db = new DbLogradouro(connection);
            Integer id = db.insert(l);
            // db.remove(t.getSigla());
            System.out.println("Get: " + db.get(id).getNome());
            // db.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
