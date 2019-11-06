/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.ctrl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.beans.EmployeesSB;
import se.m1.beans.UsersSB;
import se.m1.model.DBActionsMySql;
import se.m1.model.Employees;
import se.m1.model.Users;
import se.m1.utils.Constants;
import static se.m1.utils.Constants.*;
import se.m1.utils.Utilities;


public class LoginPageActions {

    public static DBActionsMySql dba;
    Users userInput;
    InputStream input;
    String dbUrl="";
    String dbUser="";
    String dbPwd="";
    

    

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

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet, EmployeesSB empSB, UsersSB usersSB)
            throws ServletException, IOException {

//        request.getSession().setAttribute("previousPageUrl", Constants.JSP_LOGIN_PAGE);
        
        initDBProps(servlet);
        displayEmptyFieldsErrMsg(request, response);
        
        if(request.getAttribute("errKey") == null)
        {
            if(dba == null)
             dba = new DBActionsMySql(dbUrl,dbUser,dbPwd,empSB);
            
            List<Users> dbUsers = usersSB.getAllUsers();

            //Data entered by the user
            userInput = new Users();
            userInput.setLogin(request.getParameter(FRM_LOGIN_FIELD));
            userInput.setPwd(request.getParameter(FRM_PWD_FIELD));
            
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
            request.setAttribute("errKey", ERR_MESSAGE);
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
        boolean connFieldEmpty = request.getParameter("loginField").equals("");
        boolean pwdFieldEmpty = request.getParameter("pwdField").equals("");
        if (connFieldEmpty || pwdFieldEmpty)
        {
            if(connFieldEmpty)
                request.setAttribute("errKey",Constants.LOGIN_FIELD_EMPTY_MSG);
            else
                request.setAttribute("errKey", Constants.PWD_FIELD_EMPTY_MSG);
            Utilities.NavigateAndSavePrevPage(request, response, JSP_LOGIN_PAGE);
//          request.getRequestDispatcher(JSP_LOGIN_PAGE).forward(request, response);
        }
    }
    
    void initDBProps(HttpServlet servlet) throws IOException
    {
        Properties prop = new Properties();
        input = servlet.getServletContext().getResourceAsStream(Constants.FILE_PROPERTIES_DB_PATH);
        if(input == null)
            throw new NullPointerException("DB Properties file not found");
        prop.load(input);
        dbUrl = prop.getProperty("dbUrl");
        dbUser = prop.getProperty("dbUser");
        dbPwd = prop.getProperty("dbPwd");            

    }
   
}