package com.main.model.database;

import com.main.bo.comunicacao.Telefone;
import com.main.bo.pessoa.Contribuinte;

public class DbContribuinte {
    private DbConnection connection;
    private DbEndereco dbEndereco;
    private DbFoneContribuinte foneContri;
    private DbEmailContribuinte emailContri;
    private DbFone fone;

    public DbContribuinte(DbConnection connection) {
        this.connection = connection;
        this.dbEndereco = new DbEndereco(connection);
        this.foneContr = new DbFoneContribuinte(connection);
        this.emailContri = new DbEmailContribuinte(connection);
        this.fone = new DbFone(connection);
    }

    public Integer insert(Contribuinte contribuinte) throws Exception {
        try {
            this.connection.startTransition();
            Integer idEndereco;
            try {

                idEndereco = this.dbEndereco.getId(contribuinte.getEnderecoResidencial().getEndereco().getCep());
            } catch (Exception e) {
                e.printStackTrace();
                idEndereco = this.dbEndereco.insert(contribuinte.getEnderecoResidencial().getEndereco());
            }

            String names[] = new String[] { "nomeContribuinte", "sobrenomeContribuinte", "nomeSocialContribuinte",
                    "cpfContribuinte", "rgContribuinte", "sexoContribuinte", "numeroEnderecoContribuinte",
                    "Endereco_idEndereco" };
            String values[] = new String[] { contribuinte.getNome(), contribuinte.getSobrenome(),
                    contribuinte.getNomeSocial(), contribuinte.getCpf(), contribuinte.getRg(), contribuinte.getSexo(),
                    contribuinte.getEnderecoResidencial().getNroCasa().toString(), idEndereco.toString() };
            Integer aux = this.connection.insert("Contribuinte", names, values).getInt(1);

            this.connection.commit();

            for (Telefone t : contribuinte.getTelefones()) {
                Integer telAux;
                try {
                    telAux = this.fone.getId(t);
                } catch (Exception e) {
                    telAux = this.fone.insert(t);
                }

                try {
                    this.foneContri.getId(aux, telAux);
                } catch (Exception e) {
                    this.foneContri.insert(t, aux);
                }
            }

            return res;
        } catch (SQLIntegrityConstraintViolationException e1) {
            throw new Exception("Ja inserido");
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção de Bairro revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public Bairro get(Integer idBairro) throws Exception {
        String sql = "SELECT * FROM Bairros WHERE idBairro='" + idBairro + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            Bairro bairro = new Bairro(res.getString("nomeBairro"));
            return bairro;
        }
        throw new Exception("Bairro não encontrado.");
    }

    public Integer get(Bairro bairro) throws Exception {
        String sql = "SELECT * FROM Bairros WHERE nomeBairro='" + bairro.getNome() + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            return res.getInt(1);
        }
        throw new Exception("Bairro não encontrado.");
    }

}
