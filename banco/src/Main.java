package src;

import src.bo.endereco.*;
import src.model.DbConnection;
import src.model.*;

public class Main {
    public static void main(String args[]) {
        try {
            DbConnection connection = new DbConnection("user", "123");
            DbEndereco dB = new DbEndereco(connection);
            Logradouro l = new Logradouro("logradouro", new TipoLogradouro("tipoLogradouro", "siglaLogradouro"));
            Cidade c = new Cidade("cidade", new Uf("uf", "siglaUf"));
            Bairro b = new Bairro("bairro");
            Endereco e = new Endereco("12345", l, b, c);
            dB.insert(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
