/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.ctrl;

import se.m1.model.EmployeesDetailsPageActions;
import se.m1.model.EmployeesListPageActions;
import se.m1.model.LoginPageActions;
import se.m1.model.NavbarActions;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.beans.EmployeesSB;
import se.m1.beans.UsersSB;
import se.m1.utils.Constants;

/**
 *
 * @author franc
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    
    @EJB
    public  UsersSB usersSB;
    
    @EJB
    public  EmployeesSB empSB;
    
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
                 
        try {
            

            // Premier lancement du programme

        String context = request.getParameter("action");
        System.out.println("Command: "+context);
        if(context != null){
        switch(context)
        {     
            case "Connect": 
               new LoginPageActions().processRequest(request, response, this);
               break;
            case "Add":
            case "Delete":
            case "Details":
                new EmployeesListPageActions().processRequest(request, response);
                break;
            case "Save":
            case "Cancel":
            case "Go Back":
            case "Create":
                new EmployeesDetailsPageActions().processRequest(request, response);
                break;
            //A DIVISER EN PLUSIEURS FONCTIONS
            case "Disconnect":
            case "Go to the Login Page":
                new NavbarActions().processRequest(request, response);
                break;
            default:
                //nouvelle exception not found
                //page jsp not found
        }
        }
    }   catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
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
