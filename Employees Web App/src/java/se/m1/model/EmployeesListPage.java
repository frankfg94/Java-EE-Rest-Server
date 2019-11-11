
package se.m1.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.utils.Constants;



public class EmployeesListPage{

    User userInput;
    InputStream input;
    String dbUrl="";
    String dbUser="";
    String dbPwd="";

    private TreeMap<Integer,Employee> employees;
    
    int selEmployeeId;
    
    public void addAnEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Retrieve data
        employees = (TreeMap<Integer,Employee>)request.getSession().getAttribute("empList");
        System.out.println("Add button clicked");
        
        request.getRequestDispatcher(Constants.JSP_EMPLOYEES_DETAILS_PAGE).forward(request, response); 
    }
    
    public void employeesDetails(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        //Retrieve data
        employees = (TreeMap<Integer,Employee>)request.getSession().getAttribute("empList");
        System.out.println("Edit/Details button clicked");
        if(request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME)!= null)
            selEmployeeId = Integer.parseInt(request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME));
        
        request.setAttribute("selEmployee", employees.get(selEmployeeId));
        request.getSession().setAttribute("previousPageUrl",Constants.JSP_EMPLOYEESLIST_PAGE);
        if((boolean)request.getSession().getAttribute("isAdmin"))
            request.getRequestDispatcher(Constants.JSP_EMPLOYEES_DETAILS_PAGE).forward(request, response);
        else 
            request.getRequestDispatcher(Constants.JSP_EMPLOYEES_DETAILS_EMP_PAGE).forward(request, response);
    }
    
    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Retrieve data
        employees = (TreeMap<Integer,Employee>)request.getSession().getAttribute("empList");
        System.out.println("Delete button clicked " + request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME));
        if(request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME)!= null)
            selEmployeeId = Integer.parseInt(request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME));
        
        request.setAttribute("selEmployee", employees.get(selEmployeeId));
        try {
            LoginPage.dba.DeleteEmployee(employees.get(selEmployeeId),selEmployeeId);
            employees.remove(selEmployeeId);
        } catch (Exception ex) {
            Logger.getLogger(EmployeesListPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
    }
}
