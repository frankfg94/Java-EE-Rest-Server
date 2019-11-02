/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.ctrl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.model.DBActions;
import se.m1.model.*;
import se.m1.model.User;
import se.m1.utils.Constants;
import static se.m1.utils.Constants.*;


public class Controller extends HttpServlet {

    public static DBActions dba;
    User userInput;
    InputStream input;
    String dbUrl="";
    String dbUser="";
    String dbPwd="";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String context = request.getParameter("action");
        System.out.println("Context: "+context);
        if(context != null){
        switch(context)
        {     
            case "Connect": 
               new LoginPage().loginAttempt(request, response, this);
               break;
            case "Add":
                new EmployeesListPage().addAnEmployee(request, response);
                break;
            case "Delete":
                new EmployeesListPage().deleteEmployee(request, response);
                break;
            case "Details":
                new EmployeesListPage().employeesDetails(request, response);
                break;
            case "Save":
                new EmployeesDetailsPage().saveEmployee(request, response);
                break;
            case "Cancel":
                new EmployeesDetailsPage().cancelEmployeesCreation(request, response);
                break;
            case "Go Back":
                new EmployeesDetailsPage().goBackToEmpList(request, response);
                break;
            case "Create":
                new EmployeesDetailsPage().createNewEmployee(request, response);
                break;
            //A DIVISER EN PLUSIEURS FONCTIONS
            case "Disconnect":
                new Navbar().manageAction(request, response);
                break;
            case "Go to the Login Page":
                new Navbar().manageAction(request, response);
                break;
            default:
                //nouvelle exception not found
                //page jsp not found
        }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
            processRequest(request, response);
        
        
        
        
    }
    
    
    public void loginAttempt(HttpServletRequest request, HttpServletResponse response, GenericServlet servlet)
            throws ServletException, IOException {

        Properties prop = new Properties();
        input = request.getServletContext().getResourceAsStream("/WEB-INF/db.properties");
        
        prop.load(input);
        
        dbUrl = prop.getProperty("dbUrl");
        dbUser = prop.getProperty("dbUser");
        dbPwd = prop.getProperty("dbPwd");
        
            

        boolean connFieldEmpty = request.getParameter("loginField").equals("");
        boolean pwdFieldEmpty = request.getParameter("pwdField").equals("");
        if (connFieldEmpty || pwdFieldEmpty) {
            if(connFieldEmpty)
                request.setAttribute("errKey",Constants.LOGIN_FIELD_EMPTY_MSG);
            else
                request.setAttribute("errKey", Constants.PWD_FIELD_EMPTY_MSG);
            request.getRequestDispatcher(JSP_LOGIN_PAGE).forward(request, response);
        } else {
            dba = new DBActions(dbUrl,dbUser,dbPwd);

            ServletContext ctx = request.getServletContext();
            ServletConfig conf = servlet.getServletConfig();

            // Obtenu via le fichier web.xml
            String loginAdminCtx = ctx.getInitParameter("loginAdmin");
            String pwdAdminCtx = conf.getInitParameter("passwordAdmin");

            String loginEmpCtx = ctx.getInitParameter("loginEmp");
            String pwdEmpCtx = conf.getInitParameter("passwordEmp");
            
            //Data entered by the user
            userInput = new User();
            userInput.setLogin(request.getParameter(FRM_LOGIN_FIELD));
            userInput.setPwd(request.getParameter(FRM_PWD_FIELD));

            //if (dba.checkCredentials(userInput)) {
            request.getSession().setAttribute("username", userInput.getLogin());
            
            // NULL REFERENCE EXCEPTION POUR L'INSTANT
            request.setAttribute("previousPageUrl",Constants.JSP_LOGIN_PAGE);
            if (loginAdminCtx.equals(userInput.getLogin()) && pwdAdminCtx.equals(userInput.getPwd())) {
                request.getSession().setAttribute("empList", dba.getEmployees());
                request.getSession().setAttribute("isAdmin", true);
                request.getRequestDispatcher(JSP_EMPLOYEESLIST_PAGE).forward(request, response);
            } 
            else if(loginEmpCtx.equals(userInput.getLogin()) && pwdEmpCtx.equals(userInput.getPwd()))
            {
               request.getSession().setAttribute("empList", dba.getEmployees());
               request.getSession().setAttribute("isAdmin", false);
               request.getRequestDispatcher(JSP_EMPLOYEESLIST_EMP_PAGE).forward(request, response);
            }
            else
            {
                request.setAttribute("errKey", ERR_MESSAGE);
                request.getRequestDispatcher(JSP_LOGIN_PAGE).forward(request, response);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
