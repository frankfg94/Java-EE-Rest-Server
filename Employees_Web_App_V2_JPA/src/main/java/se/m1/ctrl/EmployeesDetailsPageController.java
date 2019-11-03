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

/**
 *
 * @author JAA
 */
public class EmployeesDetailsPageController extends HttpServlet {

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
              boolean saveButClicked =request.getParameter(Constants.SAVE_EMP_DETAILS_BUT_NAME) != null;
              boolean cancelButClicked = request.getParameter(Constants.CANCEL_EMP_DETAILS_BUT_NAME) != null;
              boolean createButClicked = request.getParameter(Constants.CREATE_EMP_BUT_NAME) != null;
              if(saveButClicked)
              {
                  saveButAction(request, response);
              }
              else if(cancelButClicked)
              {
                  cancelButAction(request, response);
              } 
              else if (createButClicked)
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
             if((boolean)(request.getSession().getAttribute("isAdmin")))
                    request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
                  else
                    request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_EMP_PAGE).forward(request, response);
        }
        
        void createButAction(HttpServletRequest request, HttpServletResponse response) throws Exception
        {
                  addNewEmployee(request);
                  
                  // On active le mode création
                  request.getSession().setAttribute("selEmployee", null);    
                  request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
        }
    
    private void editCurrentEmployee( HttpServletRequest request) throws Exception
    {


        if(selEmployee == null)
            System.out.println("Employee is NULL, can't save it");
        
       selEmployee.setName( request.getParameter("empName"));
       selEmployee.setFirstname(request.getParameter("empFirstName"));
       selEmployee.setTelhome(request.getParameter("empTelhome"));
       selEmployee.setTelhome(request.getParameter("empTelmob"));
       selEmployee.setTelpro(request.getParameter("empTelpro"));
       selEmployee.setAdress(request.getParameter("empAdress"));
       selEmployee.setPostalcode(request.getParameter("empPostalcode"));
       selEmployee.setCity(request.getParameter("empCity"));
       selEmployee.setEmail(request.getParameter("empEmail"));
       
       if(LoginPageController.dba == null)
            System.out.println("null");
       LoginPageController.instance.empSB.EditEmployee(selEmployee);
        System.out.println("Employee Update Done");
    }
    
        private void addNewEmployee(HttpServletRequest request) {
         
       Employees employeeFromForm = new Employees();
       
       employeeFromForm.setName( request.getParameter("empName"));
       employeeFromForm.setFirstname(request.getParameter("empFirstname"));
       employeeFromForm.setTelhome(request.getParameter("empTelhome"));
       employeeFromForm.setTelmob(request.getParameter("empTelmob"));
       employeeFromForm.setTelpro(request.getParameter("empTelpro"));
       employeeFromForm.setAdress(request.getParameter("empAdress"));
       employeeFromForm.setPostalcode(request.getParameter("empPostalcode"));
       employeeFromForm.setCity(request.getParameter("empCity"));
       String s = request.getParameter("empTelmob");
            System.out.println(s);
       employeeFromForm.setEmail(request.getParameter("empEmail"));
       
                       LoginPageController.instance.empSB.AddEmployee(employeeFromForm);

        
    
       
       // Actualise la liste
       request.getSession().setAttribute("empList", LoginPageController.instance.empSB.getAllEmployeesDict());
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
