package se.m1.model;

import se.m1.ctrl.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.beans.EmployeesSB;
import se.m1.utils.Constants;
import se.m1.utils.Utilities;

public class EmployeesListPage {

    InputStream input;

    // Informations pour la base de données
    private String dbUrl = "";
    private String dbUser = "";
    private String dbPwd = "";
    private TreeMap<Integer, Employees> employees;
    private int selEmployeeId;

    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employees = (TreeMap<Integer, Employees>) request.getSession().getAttribute("empList");
        System.out.println("Delete button clicked " + request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME));

        if (request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME) != null) {
            selEmployeeId = Integer.parseInt(request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME));
        } else {
            request.setAttribute("errRadioButton", "Veuillez d'abord selectionner un employé");
            request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
        }

        if (!employees.containsKey(selEmployeeId)){
            request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
        }

        request.getSession().setAttribute("selEmployee", employees.get(selEmployeeId));

        try {
            LoginPage.dba.deleteEmployee(employees.get(selEmployeeId));
            request.getSession().setAttribute("empList", LoginPage.dba.getAllEmployees());

            request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);

        } catch (IOException | ServletException ex) {
            Logger.getLogger(EmployeesListPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void employeeDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("empList") != null)
            employees = (TreeMap<Integer, Employees>) request.getSession().getAttribute("empList");
        else
            employees = LoginPage.dba.getAllEmployees();

        // get the id of the selected emplyee
        if (request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME) != null) {
            selEmployeeId = Integer.parseInt(request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME));
        } else {
            request.setAttribute("errRadioButton", "Veuillez d'abord selectionner un employé");
            if (Utilities.CurUserIsAdmin(request)) {
                request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_EMP_PAGE).forward(request, response);
            }
            return;
        }

        try {
            System.out.println("Edit/Details button clicked");
            Employees emp = employees.get(selEmployeeId);
            if (emp == null) {
                throw new NullPointerException("Cannot get the variable selEmployeeId from the radioButton because " + Constants.RADIO_EMPLOYEES_LIST_NAME + " is null");
            }
            request.getSession().setAttribute("selEmployee", emp);
            if (Utilities.CurUserIsAdmin(request)) {
                request.getRequestDispatcher(Constants.JSP_EMPLOYEES_DETAILS_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(Constants.JSP_EMPLOYEES_DETAILS_EMP_PAGE).forward(request, response);
            }

        } catch (ServletException | IOException ex) {
            Logger.getLogger(EmployeesListPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Retrieve data
        employees = (TreeMap<Integer, Employees>) request.getSession().getAttribute("empList");
        request.getSession().setAttribute("selEmployee", null);
        System.out.println("Add button clicked");
        request.getRequestDispatcher(Constants.JSP_EMPLOYEES_DETAILS_PAGE).forward(request, response);
    }

}
