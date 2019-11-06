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
import se.m1.beans.EmployeesSB;
import se.m1.ctrl.LoginPageActions;
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
    private final EmployeesSB empSB;
    public DBActionsMySql(String url, String user, String pwd, EmployeesSB empSB) {
        try {
            System.out.println("New DB action instance");
            
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } 
        this.empSB = empSB;
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

    public TreeMap<Integer, Employees> getAllEmployees() 
    {

        return empSB.getAllEmployeesDict();
        
    }
    
     public void insertEmployee(Employees emp)
    {
          empSB.AddEmployee(emp);
      System.out.println("Employee Added to the Database");
    }
    
    public void editEmployee(Employees e)
    {
          empSB.EditEmployee(e);
    }
    
    public void deleteEmployee(Employees e)
    {
            empSB.RemoveEmployee(e);
    }


}