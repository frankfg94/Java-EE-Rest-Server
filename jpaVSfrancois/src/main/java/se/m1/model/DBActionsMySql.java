package se.m1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.m1.ctrl.LoginPageController;
import static se.m1.utils.Constants.*;

/**
 *
 * @author JAA
 */
public class DBActionsMySql {
    Connection conn;
    Statement stmt;
    ResultSet rs;
    ArrayList<Users> listUsers;
    TreeMap<Integer,Employees> listEmployees;
    public DBActionsMySql(String url, String user, String pwd) {
        try {
            System.out.println("New DB action");
            //Class.forName("com.mysql.jdbc.Driver");
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