package src;

import src.bo.endereco.*;
import src.model.DbConnection;
import src.model.*;

public class Main {
    public static void main(String args[]) {
        try {
            DbConnection connection = new DbConnection("user", "123");
            DbBairro dB = new DbBairro(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
