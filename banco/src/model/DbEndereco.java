package src.model;

import src.bo.endereco.Endereco;

public class DbEndereco {
    private DbConnection connection;
    private DbCidade dbCidade;
    private DbLogradouro dbLogradouro;
    private DbBairro dbBairro;

    public DbEndereco(DbConnection connection) {
        this.connection = connection;
        this.dbCidade = new DbCidade(connection);
        this.dbLogradouro = new DbLogradouro(connection);
        this.dbBairro = new DbBairro(connection);
    }

    public Integer insert(Endereco endereco) {
        try {
            this.connection.startTransition();
            Integer idCidade = null, idLogradouro = null, idBairro = null;

            try {
                idCidade = this.dbCidade.get(endereco.getCidade());
            } catch (Exception e) {
                idCidade = this.dbCidade.insert(endereco.getCidade());
            }
            try {
                idLogradouro = this.dbLogradouro.get(endereco.getLogradouro());
            } catch (Exception e) {
                idLogradouro = this.dbLogradouro.insert(endereco.getLogradouro());
            }
            try {
                idBairro = this.dbBairro.get(endereco.getBairro());
            } catch (Exception e) {
                idBairro = this.dbBairro.insert(endereco.getBairro());
            }

            String names[] = new String[] { "cep", "Bairros_idBairro", "Cidades_idCidades",
                    "Logradouros_idLogradouro" };
            String values[] = new String[] { endereco.getCep(), idBairro.toString(), idCidade.toString(),
                    idLogradouro.toString() };
            Integer res = this.connection.insert("Endereco", names, values).getInt(1);
            this.connection.commit();
            return res;
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção do endereco revertido no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }
}
