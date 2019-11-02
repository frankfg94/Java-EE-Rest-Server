package se.m1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static se.m1.utils.Constants.*;

/**
 *
 * @author JAA
 */
public class DBActionsMySql {
    Connection conn;
    Statement stmt;
    ResultSet rs;
    ArrayList<User> listUsers;
    ArrayList<Employee> listEmployees;

    public DBActionsMySql(String url, String user, String pwd) {
        try {
            System.out.println("New DB action");
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("breakpoint");
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActionsMySql.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<User> getUsers() {
        System.out.println("Get Users"); 
        listUsers = new ArrayList<>();
        rs = getResultSet(QUERY_SEL_CREDENTIALS);
        try {
            while (rs.next()) {
                User userBean = new User();
                userBean.setLogin(rs.getString("LOGIN"));
                userBean.setPwd(rs.getString("PASSWORD"));

                listUsers.add(userBean);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listUsers;
    }

    public ArrayList<Employee> getEmployees() {
        System.out.println("Get Employees"); 
        listEmployees = new ArrayList<>();
        rs = getResultSet(QUERY_SEL_EMPLOYEES);
        try {
            while (rs.next()) {
                Employee emplBean = new Employee();
                emplBean.setFirstname(rs.getString("FIRSTNAME"));
                emplBean.setName(rs.getString("NAME"));
                emplBean.setAddress(rs.getString("ADRESS"));
                emplBean.setCity(rs.getString("CITY"));
                emplBean.setMail(rs.getString("EMAIL"));
                emplBean.setMobilePhone(rs.getString("TELMOB"));
                emplBean.setProPhone(rs.getString("TELPRO"));
                emplBean.setHomePhone(rs.getString("TELHOME"));
                emplBean.setId(rs.getString("ID"));
                emplBean.setPostalCode(rs.getString("POSTALCODE"));
                listEmployees.add(emplBean);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listEmployees;
    }

    /**
     *
     * @param userInput
     * @return
     */
    public boolean checkCredentials(User userInput) {
        boolean testCheck = false;
        listUsers = getUsers();

        for (User userBase : listUsers) {
            if (userBase.getLogin().equals(userInput.getLogin())
                    && userBase.getPwd().equals(userInput.getPwd())) {
                testCheck = true;
            }
        }
        return testCheck;
    }

     public void DeleteEmployee(Employee emp, String id) throws Exception {
        
        if(id == null)
            throw new Exception("Impossible d'utiliser la fonction DeleteEmployee si l'identifiant 'id' est null ");
        
        try {
            
            // Prepared statements augmentent la sécurité
            String query = "DELETE FROM EMPLOYEES WHERE ID = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, id);
            preparedStmt.executeUpdate();
            System.out.println("Datas mises à jour : " + preparedStmt.getUpdateCount());
        } catch (SQLException ex) {
            Logger.getLogger(DBActionsMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void SaveEmployee(Employee emp, String id) throws Exception {
        
        if(id == null)
            throw new Exception("Impossible d'utiliser la fonction SaveEmployee si l'identifiant 'id' est null ");
        
        try {
            
            // Prepared statements augmentent la sécurité
            String query = "UPDATE EMPLOYEES  SET NAME = ?, FIRSTNAME = ?, TELHOME = ?, TELMOB = ?, TELPRO = ?, ADRESS  = ?, POSTALCODE = ?, CITY = ?, EMAIL = ? WHERE ID = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, emp.getName());
            preparedStmt.setString(2, emp.getFirstname());
            preparedStmt.setString(3,emp.getHomePhone());
            preparedStmt.setString(4, emp.getMobilePhone());
            preparedStmt.setString(5, emp.getProPhone());
            preparedStmt.setString(6,emp.getAddress());
            preparedStmt.setString(7,emp.getPostalCode());
            preparedStmt.setString(8, emp.getCity());
            preparedStmt.setString(9, emp.getMail());
            preparedStmt.setString(10, id);
            preparedStmt.execute(id);
            System.out.println("Datas mises à jour : " + preparedStmt.getUpdateCount());
        } catch (SQLException ex) {
            Logger.getLogger(DBActionsMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public void AddEmployee(Employee emp) {
        try {
            String query = "INSERT INTO EMPLOYEES(NAME,FIRSTNAME,TELHOME,TELMOB,TELPRO,ADRESS,POSTALCODE,CITY,EMAIL) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, emp.getName());
            preparedStmt.setString(2, emp.getFirstname());
            preparedStmt.setString(3,emp.getHomePhone());
            preparedStmt.setString(4, emp.getMobilePhone());
            preparedStmt.setString(5, emp.getProPhone());
            preparedStmt.setString(6,emp.getAddress());
            preparedStmt.setString(7,emp.getPostalCode());
            preparedStmt.setString(8, emp.getCity());
            preparedStmt.setString(9, emp.getMail());
            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBActionsMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}