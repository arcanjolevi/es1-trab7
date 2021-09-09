package com.main.model.database;

import java.sql.*;

public class DbConnection {
  private Connection connection = null;
  private String ip = "localhost";
  private String port = "3306";
  private String dbName = "mydb";
  private String url = "jdbc:mariadb://" + ip + ":" + port + "/" + dbName;

  public DbConnection(String user, String pass) throws SQLException{
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

  public void execute(String query) throws SQLException {
    Statement stmQuery = this.connection.createStatement();
    stmQuery.execute(query);
  }

  public void commit() throws SQLException {
    this.connection.commit();
  }

  public Statement createStatement() throws SQLException {
    return this.connection.createStatement();
  }

  public void insert(String table, String values[]) {
    String sql = "INSERT INTO " + table + " VALUES (";
    for(int i = 0; i < values.length; i++) {
      sql += values[i];
      if(i != values.length-1) sql += ",";
    }
    sql += ");";
    try {
      this.execute(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
