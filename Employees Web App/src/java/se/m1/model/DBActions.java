package se.m1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static se.m1.utils.Constants.*;

public class DBActions {        //class that handles the link between the DB and the program

    Connection conn;
    Statement stmt;
    ResultSet rs;
    ArrayList<User> listUsers;
    TreeMap<Integer, Employee> listEmployees;

    public DBActions(String url, String user, String pwd) {
        try {
            System.out.println("New DB action");
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("breakpoint");
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public Statement getStatement()
            throws SQLException {
        System.out.println("Get Statement");
        stmt = conn.createStatement();

        return stmt;

    }

    public ResultSet getResultSet(String query)
            throws SQLException {
        System.out.println("Get Result Set");
        stmt = getStatement();
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return rs;

    }

    public ArrayList<User> getUsers()
            throws SQLException {
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

    public TreeMap<Integer, Employee> getEmployees()
            throws SQLException {
        System.out.println("Get Employees");
        listEmployees = new TreeMap<Integer, Employee>();
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
                emplBean.setId(rs.getInt("ID"));
                emplBean.setPostalCode(rs.getString("POSTALCODE"));
                listEmployees.put(emplBean.getId(), emplBean);
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
    public boolean checkCredentials(User userInput)
            throws SQLException {
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

    public void DeleteEmployee(Employee emp, int id) throws Exception {

        try {

            // Prepared statements to improve security
            String query = "DELETE FROM EMPLOYEES WHERE ID = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            System.out.println("Datas mises à jour : " + preparedStmt.getUpdateCount());
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void SaveEmployee(Employee emp, int id) {

        try {

            // Prepared statements to improve security
            String query = "UPDATE EMPLOYEES  SET NAME = ?, FIRSTNAME = ?, TELHOME = ?, TELMOB = ?, TELPRO = ?, ADRESS  = ?, POSTALCODE = ?, CITY = ?, EMAIL = ? WHERE ID = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, emp.getName());
            preparedStmt.setString(2, emp.getFirstname());
            preparedStmt.setString(3, emp.getHomePhone());
            preparedStmt.setString(4, emp.getMobilePhone());
            preparedStmt.setString(5, emp.getProPhone());
            preparedStmt.setString(6, emp.getAddress());
            preparedStmt.setString(7, emp.getPostalCode());
            preparedStmt.setString(8, emp.getCity());
            preparedStmt.setString(9, emp.getMail());
            preparedStmt.setInt(10, id);
            preparedStmt.execute();
            System.out.println("Datas mises à jour : " + preparedStmt.getUpdateCount());
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void AddEmployee(Employee emp) {
        try {
            String query = "INSERT INTO EMPLOYEES(NAME,FIRSTNAME,TELHOME,TELMOB,TELPRO,ADRESS,POSTALCODE,CITY,EMAIL) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, emp.getName());
            preparedStmt.setString(2, emp.getFirstname());
            preparedStmt.setString(3, emp.getHomePhone());
            preparedStmt.setString(4, emp.getMobilePhone());
            preparedStmt.setString(5, emp.getProPhone());
            preparedStmt.setString(6, emp.getAddress());
            preparedStmt.setString(7, emp.getPostalCode());
            preparedStmt.setString(8, emp.getCity());
            preparedStmt.setString(9, emp.getMail());
            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
