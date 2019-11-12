/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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


public class LoginPageActions {

    public  static DBActionsREST dba;
    Users userInput;
    InputStream input;
    String dbUrl="";
    String dbUser="";
    String dbPwd="";
    public UsersSB usersSB;
    public EmployeesSB empSB;
    
     
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @param servlet
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    
    public void processRequest(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet)
            throws ServletException, IOException, NamingException {

//        request.getSession().setAttribute("previousPageUrl", Constants.JSP_LOGIN_PAGE);
        InitialContext ic = new InitialContext();
         usersSB = (UsersSB)ic.lookup("java:global/Employees_Web_App_V3_REST/UsersSB!se.m1.beans.UsersSB");
         empSB = (EmployeesSB)ic.lookup("java:global/Employees_Web_App_V3_REST/EmployeesSB!se.m1.beans.EmployeesSB");

        initDBProps(servlet);
        displayEmptyFieldsErrMsg(request, response);
        
        if(request.getAttribute(Constants.ERR_MSG) == null)
        {
            if(dba == null)
             dba = new DBActionsREST(dbUrl,dbUser,dbPwd);
            
            List<Users> dbUsers = usersSB.getAllUsers();

            
            //Data entered by the user
            userInput = new Users();
            userInput.setLogin(request.getParameter(FRM_LOGIN_FIELD_NAME));
            userInput.setPwd(request.getParameter(FRM_PASS_FIELD_NAME));
            
            request.setAttribute("previousPageUrl",Constants.JSP_LOGIN_PAGE);
            
            for (Users dbUs : dbUsers)
            {
                   if(dbUs.VerifyCredentials(userInput.getLogin(),userInput.getPwd()))
                   {
                       loadEmpsAndSaveToRequest(request);
                       request.getSession().setAttribute("userBean", dbUs );
                       if(dbUs.getRole().equals("admin"))
                           Utilities.NavigateAndSavePrevPage(request, response, JSP_EMPLOYEESLIST_PAGE);
                       else 
                           Utilities.NavigateAndSavePrevPage(request, response, JSP_EMPLOYEESLIST_EMP_PAGE);
                       return;
                   }
            }
            request.setAttribute(Constants.ERR_MSG, ERR_MESSAGE);
            Utilities.NavigateAndSavePrevPage(request, response, JSP_LOGIN_PAGE);
        }
    }

    void loadEmpsAndSaveToRequest (HttpServletRequest request)
    {
        TreeMap<Integer, Employees> emps =  dba.getAllEmployees();
        request.getSession().setAttribute("empList",emps );
        ArrayList<Integer> ints = new ArrayList<>();
        ints.addAll(emps.keySet());
        request.getSession().setAttribute("empKeys", ints  );
    }
    
    void displayEmptyFieldsErrMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        boolean connFieldEmpty = request.getParameter("loginField").isEmpty();
        boolean pwdFieldEmpty = request.getParameter("pwdField").isEmpty();
        if (connFieldEmpty || pwdFieldEmpty)
        {
            if(connFieldEmpty)
                request.setAttribute(ERR_MSG,Constants.LOGIN_FIELD_EMPTY_MSG);
            else
                request.setAttribute(ERR_MSG, Constants.PASS_FIELD_EMPTY_MSG);
            Utilities.NavigateAndSavePrevPage(request, response, JSP_LOGIN_PAGE);
//          request.getRequestDispatcher(JSP_LOGIN_PAGE).forward(request, response);
        }
    }
    
    void initDBProps(HttpServlet servlet) throws IOException
    {
        Properties prop = new Properties();
        input = servlet.getServletContext().getResourceAsStream(FILE_PROPERTIES_DB_PATH);
        prop.load(input);
        dbUrl = prop.getProperty("dbUrl");
        dbUser = prop.getProperty("dbUser");
        dbPwd = prop.getProperty("dbPwd");            

    }
   
}