package com.main.model.database;

import java.sql.ResultSet;

import com.main.bo.bensedireitos.BensEDireitos;
import com.main.bo.bensedireitos.TipoBensEDireitos;

public class DbDireitosBens {
    private DbConnection connection;
    private DbTipoDireitoBens dTD;

    public DbDireitosBens(DbConnection connection) {
        this.connection = connection;
        this.dTD = new DbTipoDireitoBens(connection);
    }

    public Integer insert(BensEDireitos bed, String cpf) {
        try {
            this.connection.startTransition();
            TipoBensEDireitos t = new TipoBensEDireitos(bed.getTipoBem());
            Integer tipoId;
            try {
                tipoId = this.dTD.insert(t);
            } catch (Exception e) {
                if (e.getMessage().compareTo("Ja inserido") == 0) {
                    tipoId = this.dTD.getId(t.getNome());
                } else {
                    throw new Exception("Não foi possível inserir ou buscar o TipoDireitoBens no banco.");
                }
            }
            String names[] = new String[] { "valorTotalDireitoBem", "TipoDireitoBem_idTipoDireitoBem",
                    "Contribuinte_cpfContribuinte" };
            String values[] = new String[] { bed.getValor().toString(), tipoId.toString(), cpf };
            Integer res = this.connection.insert("DireitosBens", names, values).getInt(1);
            this.connection.commit();
            return res;
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção do DireitosBens revertido no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }

    public BensEDireitos get(Integer idDB) throws Exception {
        String sql = "SELECT * FROM DireitosBens WHERE idDireitoBem='" + idDB + "';";
        ResultSet res = this.connection.createStatement().executeQuery(sql);
        if (res.next()) {
            TipoBensEDireitos t = new TipoBensEDireitos(this.dTD.get(res.getString("TipoDireitoBem_idDireitoBem")));
            BensEDireitos bed = new BensEDireitos(Double.parseDouble(res.getString("valorTotalDireitoBem")), t);
            return bed;
        }
        throw new Exception("DireitosBens não encontrado.");
    }

    public void remove(Integer idBed) {
        try {
            this.connection.startTransition();
            String sql = "DELETE FROM DireitosBens WHERE idDireitoBem='" + idBed + "';";
            this.connection.execute(sql);
            this.connection.commit();
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Remoção do DireitosBens revertido no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
    }
}
