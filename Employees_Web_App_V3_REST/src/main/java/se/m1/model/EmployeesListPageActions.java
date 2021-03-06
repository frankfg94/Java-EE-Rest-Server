/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.utils.Constants;
import se.m1.utils.Utilities;

/**
 *
 * @author JAA
 */
public class EmployeesListPageActions {

    InputStream input;

    // Informations pour la base de données
    String dbUrl = "";
    String dbUser = "";
    String dbPwd = "";

    private static final Logger LOGGER = Logger.getLogger(EmployeesDetailsPageActions.class.getName());

    // TODO : mettre ces variables directement dans EmployeesListPage.jsp
    final static String DELETE_EMPLOYEE_BUTTON_NAME = "delEmpButton";
    final static String EDIT_EMPLOYEE_BUTTON_NAME = "detailsEmpButton";
    final static String ADD_EMPLOYEE_BUTTON_NAME = "addEmpButton";
    Map<Integer, Employees> employees;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        employees = (TreeMap<Integer, Employees>) request.getSession().getAttribute("empList");
        handleEmployeesListPageActions(request, response);
    }

    int selEmployeeId;

    private void handleEmployeesListPageActions(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServletException, IOException {

        if (request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME) != null) {
            selEmployeeId = Integer.parseInt(request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME));
        }

        String clickedValue = (String) request.getParameter("action");

        switch (clickedValue) {
            case "Delete":
                DelEmployeeById(request, response, selEmployeeId);
                break;
            case "Details":
                EditEmployeeById(request, response, selEmployeeId);
                break;
            case "Add":
                AddEmployee(request, response);
                break;
            default:
                System.out.println("Unknown button clicked");
                // On revient sur la page des employes
                Utilities.NavigateAndSavePrevPage(request, response, Constants.JSP_EMPLOYEESLIST_PAGE);
                break;
        }

    }

    void DelEmployeeById(HttpServletRequest request, HttpServletResponse response, int id) {
        LOGGER.log(Level.FINE, "Delete button clicked {0}", request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME));
        request.getSession().setAttribute(Constants.SEL_EMPLOYEE_NAME, employees.get(id));
        try {
            LoginPageActions.dba.deleteEmployee(employees.get(id));
            if (Utilities.CurUserIsAdmin(request)) {
                request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_EMP_PAGE).forward(request, response);
            }
        } catch (IOException | ServletException ex) {
            Logger.getLogger(EmployeesListPageActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void EditEmployeeById(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            System.out.println("Edit/Details button clicked");
            Employees emp = employees.get(selEmployeeId);
            if (emp == null) {
                throw new NullPointerException("Cannot get the variable selEmployeeId from the radioButton because " + Constants.RADIO_EMPLOYEES_LIST_NAME + " is null");
            }
            request.getSession().setAttribute(Constants.SEL_EMPLOYEE_NAME, emp);
            request.getSession().setAttribute("previousPageUrl", Constants.JSP_EMPLOYEESLIST_PAGE);

            if (Utilities.CurUserIsAdmin(request)) {
                request.getRequestDispatcher(Constants.JSP_EMPLOYEES_DETAILS_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(Constants.JSP_EMPLOYEES_DETAILS_EMP_PAGE).forward(request, response);
            }

            LOGGER.log(Level.FINE, "Employee Update Done");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(EmployeesListPageActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AddEmployee(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute(Constants.SEL_EMPLOYEE_NAME, null);
            System.out.println("Add button clicked");
            Utilities.NavigateAndSavePrevPage(request, response, Constants.JSP_EMPLOYEES_DETAILS_PAGE);

        } catch (ServletException | IOException ex) {
            Logger.getLogger(EmployeesListPageActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
