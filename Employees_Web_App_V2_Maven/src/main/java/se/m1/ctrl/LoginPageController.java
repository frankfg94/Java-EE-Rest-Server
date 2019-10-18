/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.ctrl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.model.User;
import se.m1.model.DBActionsMySql;
import se.m1.utils.Constants;
import static se.m1.utils.Constants.*;


public class LoginPageController extends HttpServlet {

    public static DBActionsMySql dba;
    User userInput;
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
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Properties prop = new Properties();
        input = getServletContext().getResourceAsStream("/WEB-INF/db.properties");
        
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
            dba = new DBActionsMySql(dbUrl,dbUser,dbPwd);

            ServletContext ctx = this.getServletContext();
            ServletConfig conf = this.getServletConfig();

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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
