package br.ufsm.csi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConectaDB {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/project_poow";
    private static final String USER = "postgres";
    private static final String PASS = "1909";
    public Connection getConexao(){
        Connection conn = null;
        try {
            Class.forName(this.DRIVER);
            conn = DriverManager.getConnection(this.URL, this.USER, this.PASS);
        } catch (Exception error){
            error.printStackTrace();
        }
        return conn;
    }
}
