package se.m1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBActionsMySql {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    /**
     * Establish the connection to the database
     */
    public DBActionsMySql(String url, String user, String pwd) {
        try {
            System.out.println("New DB action instance");
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public Statement getStatement() {
        System.out.println("Get Statement");
        try {
            stmt = conn.createStatement();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return stmt;

    }

    public ResultSet getResultSet(String query) {
        System.out.println("Get Result Set");
        stmt = getStatement();
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return rs;

    }
}
