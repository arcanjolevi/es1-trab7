package com.main.model.database;

import com.main.bo.pessoa.Empresa;

public class DbEmpresa {
    private DbConnection connection;

    public DbEmpresa(DbConnection connection) {
        this.connection = connection;
    }

    public String insert(Empresa empresa) throws Exception {

        try {
            this.connection.startTransition();
            String names[] = new String[] { "siglaUf", "nomeUf" };
            String values[] = new String[] { uf.getSigla(), uf.getNome() };
            this.connection.insert("Ufs", names, values);
            Uf aux = this.get(uf.getSigla());
            this.connection.commit();
            return aux != null ? aux.getSigla() : null;
        } catch (SQLIntegrityConstraintViolationException e1) {
            throw new Exception("Ja inserido");
        } catch (Exception e) {
            try {
                this.connection.rollback();
                System.out.println("Inserção de Uf revertida no banco.");
            } catch (Exception e2) {
                System.out.println("Não foi possível reverter as alterações no banco.");
            }
        }
        return null;
    }
    /*
     * public Uf get(String sigla) throws Exception, SQLException { String sql =
     * "SELECT * FROM Ufs WHERE siglaUf='" + sigla + "';";
     * 
     * ResultSet res = this.connection.createStatement().executeQuery(sql); if
     * (res.next()) { Uf uf = new Uf(res.getString("nomeUf"),
     * res.getString("siglaUf")); return uf; } throw new
     * Exception("Uf não encontrada."); }
     * 
     * 
     * public void remove(Uf uf) { try { this.connection.startTransition(); Uf aux =
     * this.get(uf.getSigla()); String sql = "DELETE FROM Ufs WHERE siglaUf='" +
     * aux.getSigla() + "';"; this.connection.execute(sql);
     * this.connection.commit(); } catch (Exception e) { try {
     * this.connection.rollback();
     * System.out.println("Remoção de Uf revertida no banco."); } catch (Exception
     * e2) {
     * System.out.println("Não foi possível reverter as alterações no banco."); } }
     * }
     */
}
