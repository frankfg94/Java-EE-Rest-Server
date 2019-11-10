package se.m1.ctrl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.beans.EmployeesSB;
import se.m1.beans.UsersSB;
import se.m1.model.EmployeesDetailsPage;
import se.m1.model.EmployeesListPage;
import se.m1.model.LoginPage;
import se.m1.model.Navbar;
import se.m1.utils.Constants;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    @EJB
    private UsersSB usersSB;
    @EJB
    private EmployeesSB empSB;

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
        System.out.println("JE VIENS DE DO GET");
        //processRequest(request, response);

        try {

            String context = request.getParameter("action");
            System.out.println("Command: " + context);
            if (context == null) {
                request.getRequestDispatcher(Constants.JSP_LOGIN_PAGE).forward(request, response);
            }
            switch (context) {
                case "Add"://get
                    new EmployeesListPage().addEmployee(request, response);
                    break;
                case "Disconnect"://get
                    new Navbar().Disconnection(request, response);
                    break;
                case "Save"://get
                    new EmployeesDetailsPage().saveEmployee(request, response);
                    break;
                default:
                //nouvelle exception not found
                    //page jsp not found
            }

        } catch (IOException | ServletException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        System.out.println("JE VIENS DE DO POST");
        // processRequest(request, response);

        try {

            String context = request.getParameter("action");
            System.out.println("Command: " + context);
            switch (context) {
                case "Connect": //post
                    new LoginPage().loginAttempt(request, response, this, usersSB);
                    break;
                case "Delete"://post
                    new EmployeesListPage().deleteEmployee(request, response);
                    break;
                case "Details"://post
                    new EmployeesListPage().employeeDetails(request, response);
                    break;
                case "Save"://post
                    new EmployeesDetailsPage().saveEmployee(request, response);
                    break;
                case "Cancel"://post
                    new EmployeesDetailsPage().cancelEmployeesCreation(request, response);
                    break;
                case "Go Back"://post
                    new EmployeesDetailsPage().goBackToEmpList(request, response);
                    break;
                case "Create"://post
                    new EmployeesDetailsPage().createNewEmployee(request, response);
                    break;
                case "Go to the Login Page"://post
                    new Navbar().Log(request, response);
                    break;
                default:
                //nouvelle exception not found
                //page jsp not found
                }

        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
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
