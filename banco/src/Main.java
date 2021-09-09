package src;

import src.bo.comunicacao.Telefone;
import src.model.DbConnection;
import src.model.*;

public class Main {
    public static void main(String args[]) {
        try {
            DbConnection connection = new DbConnection("user", "123");
            DbFone dB = new DbFone(connection);
            Telefone f = new Telefone("45", "55", "666666");
            dB.insert(f);
            System.out.println(dB.get(f).get(0).getNumero());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
