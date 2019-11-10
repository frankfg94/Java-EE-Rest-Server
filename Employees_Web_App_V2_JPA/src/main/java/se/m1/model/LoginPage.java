
package se.m1.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.beans.EmployeesSB;
import se.m1.beans.UsersSB;
import se.m1.utils.Constants;
import static se.m1.utils.Constants.*;
import se.m1.utils.Utilities;

public class LoginPage {

    public static DBActionsMySql dba;
    Users userInput;
    InputStream input;
    String dbUrl = "";
    String dbUser = "";
    String dbPwd = "";
    private UsersSB usersSB;
    private EmployeesSB empSB;

    public void loginAttempt(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet, UsersSB uSB)
            throws ServletException, IOException, NamingException {

        InitialContext ic = new InitialContext();
        usersSB = (UsersSB) ic.lookup("java:global/Employees_Web_App_V2_JPA/UsersSB!se.m1.beans.UsersSB");
        //empSB = (EmployeesSB) ic.lookup("java:global/Employees_Web_App_V2_JPA/EmployeesSB!se.m1.beans.EmployeesSB");

        initDBProps(servlet);
        displayEmptyFieldsErrMsg(request, response);

        if (request.getAttribute("errKey") == null) {
            if (dba == null) {
                dba = new DBActionsMySql(dbUrl, dbUser, dbPwd, empSB);
            }

            List<Users> dbUsers = usersSB.getAllUsers();

            //Data entered by the user
            userInput = new Users();
            userInput.setLogin(request.getParameter(FRM_LOGIN_FIELD));
            userInput.setPwd(request.getParameter(FRM_PWD_FIELD));

            for (Users dbUs : dbUsers) {
                if (dbUs.VerifyCredentials(userInput.getLogin(), userInput.getPwd())) {
                    loadEmpsAndSaveToRequest(request);
                    request.getSession().setAttribute("userBean", dbUs);
                    if (dbUs.getRole().equals("admin")) {
                        request.getRequestDispatcher(JSP_EMPLOYEESLIST_PAGE).forward(request, response);
                    } else {
                        request.getRequestDispatcher(JSP_EMPLOYEESLIST_EMP_PAGE).forward(request, response);
                    }
                    return;
                }
            }
            request.setAttribute("errKey", ERR_MESSAGE);
            request.getRequestDispatcher(JSP_LOGIN_PAGE).forward(request, response);
        }
    }

    void loadEmpsAndSaveToRequest(HttpServletRequest request) {
        TreeMap<Integer, Employees> emps = dba.getAllEmployees();
        request.getSession().setAttribute("empList", emps);
        ArrayList<Integer> ints = new ArrayList<>();
        ints.addAll(emps.keySet());
        request.getSession().setAttribute("empKeys", ints);
    }

    void displayEmptyFieldsErrMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean connFieldEmpty = request.getParameter("loginField").equals("");
        boolean pwdFieldEmpty = request.getParameter("pwdField").equals("");
        if (connFieldEmpty || pwdFieldEmpty) {
            if (connFieldEmpty) {
                request.setAttribute("errKey", Constants.LOGIN_FIELD_EMPTY_MSG);
            } else {
                request.setAttribute("errKey", Constants.PWD_FIELD_EMPTY_MSG);
            }
            request.getRequestDispatcher(JSP_LOGIN_PAGE).forward(request, response);
        }
    }

    public void initDBProps(HttpServlet servlet) throws IOException {
        Properties prop = new Properties();
        input = servlet.getServletContext().getResourceAsStream(Constants.FILE_PROPERTIES_DB_PATH);
        if (input == null) {
            throw new NullPointerException("DB Properties file not found");
        }
        prop.load(input);
        dbUrl = prop.getProperty("dbUrl");
        dbUser = prop.getProperty("dbUser");
        dbPwd = prop.getProperty("dbPwd");

    }

}
