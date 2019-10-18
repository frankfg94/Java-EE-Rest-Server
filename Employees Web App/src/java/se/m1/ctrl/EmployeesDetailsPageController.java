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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.model.User;
import se.m1.model.DBActions;
import se.m1.model.Employee;
import se.m1.utils.Constants;
import static se.m1.utils.Constants.*;

/**
 *
 * @author JAA
 */
public class EmployeesDetailsPageController extends HttpServlet {

    User userInput;
    InputStream input;
    String dbUrl="";
    String dbUser="";
    String dbPwd="";

    // TODO : mettre ces variables directement dans EmployeesListPage.jsp
    final static String DELETE_EMPLOYEE_BUTTON_NAME = "delEmpButton";
    final static String EDIT_EMPLOYEE_BUTTON_NAME = "detailsEmpButton";
    final static String ADD_EMPLOYEE_BUTTON_NAME ="addEmpButton";
    private  Employee selEmployee = null;
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
       
       selEmployee = (Employee) request.getSession().getAttribute("selEmployee");
       
       handleEmployeesDetailsPageActions(request, response, selEmployee);
       
    }

        private void handleEmployeesDetailsPageActions(HttpServletRequest request, HttpServletResponse response, Employee selEmployee) throws ServletException, IOException, Exception 
        {
              request.getSession().setAttribute("previousPageUrl",Constants.JSP_EMPLOYEES_DETAILS_PAGE);
              boolean saveButClicked =request.getParameter(Constants.SAVE_EMP_DETAILS_BUT_NAME) != null;
              boolean cancelButClicked = request.getParameter(Constants.CANCEL_EMP_DETAILS_BUT_NAME) != null;
              boolean createButClicked = request.getParameter(Constants.CREATE_EMP_BUT_NAME) != null;
              if(saveButClicked)
              {
                  saveEmployee(selEmployee, request);
                  request.getSession().setAttribute("selEmployee", null);
                  request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
              }
              else if(cancelButClicked)
              {
                  if((boolean)(request.getSession().getAttribute("isAdmin")))
                    request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
                  else
                    request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_EMP_PAGE).forward(request, response);
              }
              else if (createButClicked)
              {
                  addNewEmployee(request);
                  request.getSession().setAttribute("selEmployee", null);    
                  request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);

              }
              else
              {
                 System.out.println("Unknown button clicked");
              }
        }

    
    private void saveEmployee(Employee selEmployee, HttpServletRequest request) throws Exception
    {

        if(selEmployee == null)
            System.err.println("selEmployee is null, unable to do any action");

       selEmployee.setName( request.getParameter("empName"));
       selEmployee.setFirstname(request.getParameter("empFirstName"));
       selEmployee.setHomePhone(request.getParameter("empHomePhone"));
       selEmployee.setMobilePhone(request.getParameter("empMobilePhone"));
       selEmployee.setProPhone(request.getParameter("empProPhone"));
       selEmployee.setAddress(request.getParameter("empGetAddress"));
       selEmployee.setPostalCode(request.getParameter("empPostalcode"));
       selEmployee.setCity(request.getParameter("empCity"));
       selEmployee.setMail( request.getParameter("empMail"));
       
       if(LoginPageController.dba == null)
            System.out.println("null");
       LoginPageController.dba.SaveEmployee(selEmployee, selEmployee.getId());
        System.out.println("Employee Update Done");
    }
    
        private void addNewEmployee(HttpServletRequest request) {
            selEmployee = new Employee();
       selEmployee.setName( request.getParameter("empName"));
       selEmployee.setFirstname(request.getParameter("empFirstName"));
       selEmployee.setHomePhone(request.getParameter("empHomePhone"));
       selEmployee.setMobilePhone(request.getParameter("empMobilePhone"));
       selEmployee.setProPhone(request.getParameter("empProPhone"));
       selEmployee.setAddress(request.getParameter("empGetAddress"));
       selEmployee.setPostalCode(request.getParameter("empPostalcode"));
       selEmployee.setCity(request.getParameter("empCity"));
       selEmployee.setMail( request.getParameter("empMail"));
       
       LoginPageController.dba.AddEmployee(selEmployee);
       
       // Actualise la liste
       request.getSession().setAttribute("empList", LoginPageController.dba.getEmployees());
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
            Logger.getLogger(EmployeesDetailsPageController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EmployeesDetailsPageController.class.getName()).log(Level.SEVERE, null, ex);
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
