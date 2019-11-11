package se.m1.model;

import java.io.IOException;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.beans.EmployeesSB;
import se.m1.utils.Constants;
import se.m1.utils.Utilities;

public class EmployeesListPage {

    private TreeMap<Integer, Employees> employees;
    private int selEmployeeId;
    private EmployeesSB empSB;
    
    public EmployeesListPage() throws NamingException{
       empSB = (EmployeesSB) new InitialContext().lookup("java:global/Employees_Web_App_V2_JPA/EmployeesSB!se.m1.beans.EmployeesSB");
        
    }

    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employees = (TreeMap<Integer, Employees>) request.getSession().getAttribute("empList");
        System.out.println("Delete button clicked " + request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME));

        if (request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME) != null) {
            selEmployeeId = Integer.parseInt(request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME));
        } else {
            request.setAttribute("errRadioButton", "Please select an employee to delete first");
            request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
        }

        if (!employees.containsKey(selEmployeeId)){
            request.getSession().setAttribute("empList", empSB.getAllEmployeesDict());
            request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
        }

        request.getSession().setAttribute("selEmployee", employees.get(selEmployeeId));

        try {
            empSB.RemoveEmployee(employees.get(selEmployeeId));
            request.getSession().setAttribute("empList", empSB.getAllEmployeesDict());

            request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);

        } catch (IOException | ServletException ex) {
            Logger.getLogger(EmployeesListPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void employeeDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("empList") != null)
            employees = (TreeMap<Integer, Employees>) request.getSession().getAttribute("empList");
        else
            employees = empSB.getAllEmployeesDict();

        // get the id of the selected emplyee
        if (request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME) != null) {
            selEmployeeId = Integer.parseInt(request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME));
        } else {
            request.setAttribute("errRadioButton", "Please select an employee first");
            if (Utilities.CurUserIsAdmin(request)) {
                request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_EMP_PAGE).forward(request, response);
            }
            return;
        }

        try {
            System.out.println("Edit/Details button clicked");
            Employees emp = employees.get((Integer)selEmployeeId);
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
