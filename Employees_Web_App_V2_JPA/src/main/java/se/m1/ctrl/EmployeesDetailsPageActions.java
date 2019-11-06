/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.ctrl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import se.m1.model.Users;
import se.m1.model.DBActionsMySql;
import se.m1.model.Employees;
import se.m1.utils.Constants;
import static se.m1.utils.Constants.*;
import se.m1.utils.Utilities;

/**
 *
 * @author JAA
 */
public class EmployeesDetailsPageActions extends HttpServlet {

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
             editCurrentEmployee(request);
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
            addNewEmployee(request);
                  
            // On active le mode création
            request.getSession().setAttribute("selEmployee", null);
            Utilities.NavigateAndSavePrevPage(request,response,Constants.JSP_EMPLOYEESLIST_PAGE);
        }
        

        
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
    
    private void editCurrentEmployee( HttpServletRequest request) throws Exception
    {


        if(selEmployee == null)
            System.out.println("Employee is NULL, can't save it");
        
       int tempId = selEmployee.getId();
       selEmployee = GetDataFromDetailsForm(request);
       selEmployee.setId(tempId);
       
       if(LoginPageActions.dba == null)
            System.out.println("null");
       LoginPageActions.dba.editEmployee(selEmployee);
       request.getSession().setAttribute("empList", LoginPageActions.dba.getAllEmployees());
        System.out.println("Employee Update Done");
    }
    
        private void addNewEmployee(HttpServletRequest request) {
         
       Employees employeeFromForm = GetDataFromDetailsForm(request);
       LoginPageActions.dba.insertEmployee(employeeFromForm);
       
       // Actualise la liste
       request.getSession().setAttribute("empList", LoginPageActions.dba.getAllEmployees());
       System.out.println("Employee Added to the Database");
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EmployeesDetailsPageActions.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EmployeesDetailsPageActions.class.getName()).log(Level.SEVERE, null, ex);
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
