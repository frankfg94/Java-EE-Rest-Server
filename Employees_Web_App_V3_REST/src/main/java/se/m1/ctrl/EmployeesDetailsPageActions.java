/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.ctrl;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import se.m1.model.Employees;
import se.m1.utils.Constants;
import se.m1.utils.Utilities;
/**
 *
 * @author JAA
 */
public class EmployeesDetailsPageActions {


    InputStream input;
    String dbUrl="";
    String dbUser="";
    String dbPwd="";


        
    // TODO : mettre ces variables directement dans EmployeesListPage.jsp
    final static String DELETE_EMPLOYEE_BUTTON_NAME = "delEmpButton";
    final static String EDIT_EMPLOYEE_BUTTON_NAME = "detailsEmpButton";
    final static String ADD_EMPLOYEE_BUTTON_NAME ="addEmpButton";
    private Employees selEmployee = null ;
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
            throws ServletException, IOException, Exception {
//        if(Utilities.CurUserIsAdmin(request))
//            request.getSession().setAttribute("previousPageUrl", Constants.JSP_EMPLOYEES_DETAILS_PAGE);
//        else 
//            request.getSession().setAttribute("previousPageUrl", Constants.JSP_EMPLOYEESLIST_EMP_PAGE);
//        
        HttpSession sess =  request.getSession();
        selEmployee =(Employees) sess.getAttribute("selEmployee");
        
       handleEmployeesDetailsPageActions(request, response);
       
    }

        private void handleEmployeesDetailsPageActions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception 
        {
              request.getSession().setAttribute("previousPageUrl",Constants.JSP_EMPLOYEES_DETAILS_PAGE);
              String clickedValue = (String)request.getParameter("action");

              
              if(clickedValue.equals("Save"))
              {
                  saveButAction(request, response);
              }
              else if(clickedValue.equals("Cancel"))
              {
                  cancelButAction(request, response);
              }
              else if (clickedValue.equals("Create"))
              {
                  createButAction(request, response);
              }
              else
              {
                 System.out.println("Unknown button clicked");
              }
        }

        void saveButAction(HttpServletRequest request, HttpServletResponse response) throws Exception
        {
             editCurrentEmployeeFromForm(request);
             request.getSession().setAttribute("selEmployee", null);
            request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
        }
        
        void cancelButAction(HttpServletRequest request, HttpServletResponse response) throws Exception
        {
             if(Utilities.CurUserIsAdmin(request))
                 Utilities.NavigateAndSavePrevPage(request, response, Constants.JSP_EMPLOYEESLIST_PAGE);
             else
                 Utilities.NavigateAndSavePrevPage(request, response, Constants.JSP_EMPLOYEESLIST_EMP_PAGE);
        }
        
        void createButAction(HttpServletRequest request, HttpServletResponse response) throws Exception
        {
            addNewEmployeeFromForm(request);
                  
            // On active le mode création
            request.getSession().setAttribute("selEmployee", null);
            Utilities.NavigateAndSavePrevPage(request,response,Constants.JSP_EMPLOYEESLIST_PAGE);
        }
        
    // Nécéssite de provenir de la page EmployeesDetailsPage.jsp
    Employees GetDataFromDetailsForm(HttpServletRequest request)
    {

        
       Employees emp = new Employees();
       emp.setName(request.getParameter("empName"));
       emp.setFirstname(request.getParameter("empFirstname"));
       emp.setTelhome(request.getParameter("empTelhome"));
       emp.setTelmob(request.getParameter("empTelmob"));
       emp.setTelpro(request.getParameter("empTelpro"));
       emp.setAdress(request.getParameter("empAdress"));
       emp.setPostalcode(request.getParameter("empPostalcode"));
       emp.setCity(request.getParameter("empCity"));
       emp.setEmail(request.getParameter("empEmail"));
       
       return emp;
    }
    
    void editCurrentEmployeeFromForm( HttpServletRequest request) throws Exception
    {
        if(selEmployee == null)
            System.out.println("Employee is NULL, can't save it");
        
       int tempId = selEmployee.getId();
       selEmployee = GetDataFromDetailsForm(request);
       selEmployee.setId(tempId);
       
       if(LoginPageActions.dba == null)
            throw new NullPointerException("The dba Action class must not be null , it must be instantiated to edit an employee");
       
       LoginPageActions.dba.editEmployee(selEmployee);
       System.out.println("Employee Update Done");
    }
    
    void addNewEmployeeFromForm(HttpServletRequest request) {
       
       Employees emp = GetDataFromDetailsForm(request);
       LoginPageActions.dba.insertEmployee(emp);
       request.getSession().setAttribute("empList", LoginPageActions.dba.getAllEmployees());
    }
}



    