package com.main.model.database;

import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.main.bo.endereco.Endereco;
import com.main.bo.comunicacao.Email;
import com.main.bo.comunicacao.Telefone;
import com.main.bo.endereco.EnderecoEspecifico;
import com.main.bo.pessoa.Dependente;
import com.main.bo.pessoa.TipoDependente;

public class DbDependente {

    private DbConnection connection;
    private DbEndereco dbEndereco;
    private DbFoneDependente foneDependente;
    private DbEmailDependente emailDependente;
    private DbFone fone;
    private DbTipoDependente tipoDependente;
    private DbContribuinte contribuinte;

    public DbDependente(DbConnection connection) {
        this.connection = connection;
        this.dbEndereco = new DbEndereco(connection);
        this.foneDependente = new DbFoneDependente(connection);
        this.emailDependente = new DbEmailDependente(connection);
        this.fone = new DbFone(connection);
        this.tipoDependente = new DbTipoDependente(connection);
        this.contribuinte = new DbContribuinte(connection);
    }

    public Integer insert(Dependente dependente, String cpfContribuinte) throws Exception {
        try {
            this.connection.startTransition();
            Integer idEndereco;
            try {
                idEndereco = this.dbEndereco.getId(dependente.getEnderecoResidencial().getEndereco().getCep());
            } catch (Exception e) {
                idEndereco = this.dbEndereco.insert(dependente.getEnderecoResidencial().getEndereco());
            }

            Integer idTipoDep;
            try {
                idTipoDep = this.tipoDependente.getId(dependente.getTipoDependente());
            } catch (Exception e) {
                idTipoDep = this.tipoDependente.insert(dependente.getTipoDependente());
            }

            Integer idContribuinte = this.contribuinte.getId(cpfContribuinte);

            String names[] = new String[] { "nomeDependente", "sobrenomeDependente", "nomeSocialDependente",
                    "cpfDependente", "rgDependente", "sexoDependente", "numeroEnderecoDependente",
                    "Endereco_idEndereco", "complementoDependente", "Contribuinte_idContribuinte",
                    "TipoDependente_idTipoDependente" };
            String values[] = new String[] { dependente.getNome(), dependente.getSobrenome(),
                    dependente.getNomeSocial(), dependente.getCpf(), dependente.getRg(),
                    dependente.getSexo().toString(), dependente.getEnderecoResidencial().getNroCasa().toString(),
                    idEndereco.toString(), dependente.getEnderecoResidencial().getComplemento(),
                    idContribuinte.toString(), idTipoDep.toString() };
            Integer aux = this.connection.insert("Dependentes", names, values).getInt(1);

            this.connection.commit();

            for (Telefone t : dependente.getTelefones()) {
                Integer telAux;
                try {
                    telAux = this.fone.getId(t);
                } catch (Exception e) {
                    telAux = this.fone.insert(t);
                }

                try {
                    this.foneDependente.getId(aux, telAux);
                } catch (Exception e) {
                    this.foneDependente.insert(t, aux);
                }
            }

            for (Email e : dependente.getEmails()) {

                try {
                    this.emailDependente.getId(e.getEmail(), aux);
                } catch (Exception exc) {
                    this.emailDependente.insert(e, aux);
                }
            }

            return aux;
        } catch (SQLIntegrityConstraintViolationException e1) {
            e1.printStackTrace();
            throw new Exception("Ja inserido");
        } catch (Exception e) {

            e.printStackTrace();
            try {
                this.connection.rollback();
                System.out.println("Inserção de Dependente revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public Dependente get(String cpf) throws Exception {
        String sql = "SELECT * FROM Dependentes WHERE cpfDependente='" + cpf + "';";

        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            Endereco end = this.dbEndereco.get(Integer.parseInt(res.getString("Endereco_idEndereco")));

            EnderecoEspecifico enderecoResidencial = new EnderecoEspecifico(
                    Integer.parseInt(res.getString("numeroEnderecoDependente")), res.getString("complementoDependente"),
                    end);

            ArrayList<Telefone> telefones = this.foneDependente.get(Integer.parseInt(res.getString("idDependentes")));
            ArrayList<Email> emails = this.emailDependente.get(Integer.parseInt(res.getString("idDependentes")));

            TipoDependente tipoDependente = this.tipoDependente
                    .get(Integer.parseInt(res.getString("TipoDependente_idTipoDependente")));

            Dependente dependente = new Dependente(res.getString("nomeDependente"), telefones, emails,
                    enderecoResidencial, res.getString("sobrenomeDependente"), res.getString("nomeSocialDependente"),
                    res.getString("cpfDependente"), res.getString("rgDependente"),
                    res.getString("sexoDependente").charAt(0), tipoDependente);

            return dependente;
        }
        throw new Exception("Uf não encontrada.");
    }

}
