package src;

import src.bo.comunicacao.Telefone;
import src.model.DbConnection;
import src.model.*;

public class Main {
    public static void main(String args[]) {
        try {
            DbConnection connection = new DbConnection("user", "123");
            DbFone dB = new DbFone(connection);
            Telefone f = new Telefone("45", "55", "12345");
            dB.insert(f);
            System.out.println(dB.getId(f));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
