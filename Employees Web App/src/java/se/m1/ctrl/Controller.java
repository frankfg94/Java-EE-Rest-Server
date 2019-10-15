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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.model.User;
import se.m1.model.DBActions;
import static se.m1.utils.Constants.*;

/**
 *
 * @author JAA
 */
public class Controller extends HttpServlet {

    DBActions dba;
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
        dbPwd = prop.getProperty("dbPws");
        
        if (request.getParameter("action") == null) {
            request.getRequestDispatcher(JSP_HOME_PAGE).forward(request, response);
        } else {
            dba = new DBActions(dbUrl,dbUser,dbPwd);

            ServletContext ctx = this.getServletContext();
            ServletConfig conf = this.getServletConfig();

            String loginCtx = ctx.getInitParameter("login");
            //  String pwdCtx = ctx.getInitParameter("password");
            String pwdCtx = conf.getInitParameter("password");

            //Data entered by the user
            userInput = new User();
            userInput.setLogin(request.getParameter(FRM_LOGIN_FIELD));
            userInput.setPwd(request.getParameter(FRM_PWD_FIELD));

            //if (dba.checkCredentials(userInput)) {
            if (loginCtx.equals(userInput.getLogin()) && pwdCtx.equals(userInput.getPwd())) {
                request.setAttribute("empList", dba.getEmployees());
                request.getRequestDispatcher(JSP_WELCOME_PAGE).forward(request, response);
            } else {
                request.setAttribute("errKey", ERR_MESSAGE);
                request.getRequestDispatcher(JSP_HOME_PAGE).forward(request, response);
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
