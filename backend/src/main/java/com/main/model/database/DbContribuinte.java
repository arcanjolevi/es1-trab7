package com.main.model.database;

import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.main.bo.endereco.Endereco;
import com.main.bo.bensedireitos.BensEDireitos;
import com.main.bo.comunicacao.Email;
import com.main.bo.comunicacao.Telefone;
import com.main.bo.endereco.EnderecoEspecifico;
import com.main.bo.pessoa.Contribuinte;
import com.main.bo.pessoa.Rendimento;

public class DbContribuinte {
    private DbConnection connection;
    private DbEndereco dbEndereco;
    private DbFoneContribuinte foneContri;
    private DbEmailContribuinte emailContri;
    private DbFone fone;

    public DbContribuinte(DbConnection connection) {
        this.connection = connection;
        this.dbEndereco = new DbEndereco(connection);
        this.foneContri = new DbFoneContribuinte(connection);
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

            for (Email e : contribuinte.getEmails()) {

                try {
                    this.emailContri.getId(e.getEmail(), aux);
                } catch (Exception exc) {
                    this.emailContri.insert(e, aux);
                }
            }

            return aux;
        } catch (SQLIntegrityConstraintViolationException e1) {
            throw new Exception("Ja inserido");
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção de Contribuinte revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public Contribuinte get(String cpf) throws Exception {
        String sql = "SELECT * FROM Contribuinte WHERE cnpjEmpresa='" + cpf + "';";

        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            Endereco end = this.dbEndereco.get(Integer.parseInt(res.getString("Endereco_idEndereco")));

            EnderecoEspecifico enderecoResidencial = new EnderecoEspecifico(
                    Integer.parseInt(res.getString("numeroEnderecoContribuinte")),
                    res.getString("complementoContribuinte"), end);

            ArrayList<Telefone> telefones = this.foneContri.get(Integer.parseInt(res.getString("idEmpresa")));
            ArrayList<Email> emails = this.emailContri.get(Integer.parseInt(res.getString("idEmpresa")));
            Contribuinte contri = new Contribuinte(res.getString("nomeContribuinte"), telefones, emails,
                    enderecoResidencial, res.getString("sobronomeContribuinte"),
                    res.getString("nomeSocialContribuinte"), res.getString("cpfContribuinte"),
                    res.getString("rgContribuinte"), res.getString("sexoContribuinte").charAt(0),
                    new ArrayList<BensEDireitos>(), new ArrayList<Rendimento>());

            return contri;
        }
        throw new Exception("Uf não encontrada.");
    }

}
