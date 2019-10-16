package se.m1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static se.m1.utils.Constants.*;

/**
 *
 * @author JAA
 */
public class DBActions {
    Connection conn;
    Statement stmt;
    ResultSet rs;
    ArrayList<User> listUsers;
    ArrayList<Employee> listEmployees;

    public DBActions(String url, String user, String pwd) {
        try {
            System.out.println("New DB action");
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("breakpoint");
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

}