package src.model;

import java.sql.*;
import java.sql.ResultSet;

public class DbConnection {
    private Connection connection = null;
    private String ip = "localhost";
    private String port = "3306";
    private String dbName = "IRRF";
    private String url = "jdbc:mariadb://" + ip + ":" + port + "/" + dbName;

    public DbConnection(String user, String pass) throws SQLException {
        this.connection = DriverManager.getConnection(url, user, pass);
    }

    public void closeConnection() {
        try {
            if (this.connection.isClosed())
                this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAutoCommit(Boolean commit) throws SQLException {
        this.connection.setAutoCommit(commit);
    }

    public void rollback() throws SQLException {
        this.connection.rollback();
    }

    public ResultSet execute(String query) throws SQLException {
        PreparedStatement stmQuery = this.connection.prepareStatement(query, new String[] { "idCidades" });
        stmQuery.execute();
        return stmQuery.getGeneratedKeys();
    }

    public void commit() throws SQLException {
        this.connection.commit();
    }

    public Statement createStatement() throws SQLException {
        return this.connection.createStatement();
    }

    public ResultSet insert(String table, String names[], String values[]) throws Error, SQLException {
        String sql = "INSERT INTO " + table + " (";
        for (int i = 0; i < names.length; i++) {
            sql += names[i];
            if (i != names.length - 1)
                sql += ",";
        }
        sql += ") VALUES (";
        for (int i = 0; i < values.length; i++) {
            sql += "'" + values[i] + "'";
            if (i != values.length - 1)
                sql += ",";
        }
        sql += ");";
        return this.execute(sql);
    }

}
