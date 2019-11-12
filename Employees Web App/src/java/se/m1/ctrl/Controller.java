package se.m1.ctrl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());

    public static DBActions dba;
    User userInput;
    InputStream input;
    String dbUrl = "";
    String dbUser = "";
    String dbPwd = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String context = request.getParameter("action");
        System.out.println("Context: " + context);
        if (context != null) {
            try {
                switch (context) {
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
            } catch (WrongButtonException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
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
