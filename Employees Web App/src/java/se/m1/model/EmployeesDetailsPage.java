package se.m1.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.utils.Constants;

public class EmployeesDetailsPage {
    
    /**
     * User bean de l'input
     */
    User userInput;
    InputStream input;

    public void saveEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee selEmployee = (Employee) request.getSession().getAttribute("selEmployee");

        if (selEmployee == null) {
            System.err.println("selEmployee is null, unable to do any action");
        }

        selEmployee.setName(request.getParameter("empName"));
        selEmployee.setFirstname(request.getParameter("empFirstName"));
        selEmployee.setHomePhone(request.getParameter("empHomePhone"));
        selEmployee.setMobilePhone(request.getParameter("empMobilePhone"));
        selEmployee.setProPhone(request.getParameter("empProPhone"));
        selEmployee.setAddress(request.getParameter("empGetAddress"));
        selEmployee.setPostalCode(request.getParameter("empPostalCode"));
        selEmployee.setCity(request.getParameter("empCity"));
        selEmployee.setMail(request.getParameter("empMail"));

        if (LoginPage.dba == null) {
            System.out.println("null");
        }
        LoginPage.dba.SaveEmployee(selEmployee, selEmployee.getId());
        System.out.println("Employee Update Done");

        request.getSession().setAttribute("selEmployee", null);
        request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
    }

    public void cancelEmployeesCreation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
    }

    public void goBackToEmpList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_EMP_PAGE).forward(request, response);
    }

    public void createNewEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        Employee selEmployee = new Employee();
        selEmployee.setName(request.getParameter("empName"));
        selEmployee.setFirstname(request.getParameter("empFirstName"));
        selEmployee.setHomePhone(request.getParameter("empHomePhone"));
        selEmployee.setMobilePhone(request.getParameter("empMobilePhone"));
        selEmployee.setProPhone(request.getParameter("empProPhone"));
        selEmployee.setAddress(request.getParameter("empGetAddress"));
        selEmployee.setPostalCode(request.getParameter("empPostalCode"));
        selEmployee.setCity(request.getParameter("empCity"));
        selEmployee.setMail(request.getParameter("empMail"));

        LoginPage.dba.AddEmployee(selEmployee);

        // Actualise la liste
        request.getSession().setAttribute("empList", LoginPage.dba.getEmployees());
        System.out.println("Employee Added to the Database");

        //Reinitialize Variable and redirect
        request.getSession().setAttribute("selEmployee", null);
        request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
    }

}
