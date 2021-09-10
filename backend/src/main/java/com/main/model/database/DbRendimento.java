package com.main.model.database;

import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.main.bo.pessoa.Empresa;
import com.main.bo.pessoa.Rendimento;

public class DbRendimento {
    private DbConnection connection;
    private DbEmpresa empresa;

    public DbRendimento(DbConnection connection) {
        this.connection = connection;
        this.empresa = new DbEmpresa(connection);
    }

    public Integer insert(Rendimento rendimento, String cpf) throws Exception {
        try {
            this.connection.startTransition();

            String names[] = new String[] { "nomeEmpresa", "valorRecebido", "INSS", "IRRFPago", "valor13Salario",
                    "Empresas_cnpjEmpresa", "Contribuinte_cpfCOntribuinte" };
            String values[] = new String[] { rendimento.getEmpresa().getNome(), rendimento.getValor().toString(),
                    rendimento.getInss().toString(), rendimento.getIrrf().toString(),
                    rendimento.getDecimoTerceiro().toString(), rendimento.getEmpresa().getCnpj(), cpf };

            Integer res = this.connection.insert("Rendimentos", names, values).getInt(1);
            this.connection.commit();
            return res;
        } catch (SQLIntegrityConstraintViolationException e1) {
            throw new Exception("Ja inserido");
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção de rendimento revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public Rendimento get(Integer idRendimento) throws Exception {
        String sql = "SELECT * FROM Rendimentos WHERE idRendimento='" + idRendimento + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            Empresa empresa = this.empresa.get(res.getString("Empresas_cnpjEmpresa"));
            Rendimento novo = new Rendimento(Double.parseDouble(res.getString("valorRecebido")),
                    Double.parseDouble(res.getString("INSS")), Double.parseDouble(res.getString("IRRFPago")),
                    Double.parseDouble(res.getString("valor13Salario")), empresa);
            return novo;
        } else {
            throw new Exception("Contribuinte não encontrado.");
        }
    }

    public ArrayList<Rendimento> get(String cpf) throws Exception {
        String sql = "SELECT * FROM Rendimentos WHERE Contribuinte_cpfContribuinte='" + cpf + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        ArrayList<Rendimento> rendimentos = new ArrayList<Rendimento>();
        while (res.next()) {
            Empresa empresa = this.empresa.get(res.getString("Empresas_cnpjEmpresa"));
            Rendimento novo = new Rendimento(Double.parseDouble(res.getString("valorRecebido")),
                    Double.parseDouble(res.getString("INSS")), Double.parseDouble(res.getString("IRRFPago")),
                    Double.parseDouble(res.getString("valor13Salario")), empresa);
            rendimentos.add(novo);
        }
        return rendimentos;
    }
}
