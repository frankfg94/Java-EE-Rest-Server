/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.ctrl;

import java.io.IOException;
import java.io.InputStream;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.model.Employees;
import se.m1.utils.Constants;

/**
 *
 * @author JAA
 */
public class EmployeesListPageController extends HttpServlet {

    InputStream input;
    String dbUrl="";
    String dbUser="";
    String dbPwd="";

    // TODO : mettre ces variables directement dans EmployeesListPage.jsp
    final static String DELETE_EMPLOYEE_BUTTON_NAME = "delEmpButton";
    final static String EDIT_EMPLOYEE_BUTTON_NAME = "detailsEmpButton";
    final static String ADD_EMPLOYEE_BUTTON_NAME ="addEmpButton";
    TreeMap<Integer,Employees> employees;
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
            throws ServletException, IOException {
       
       employees = (TreeMap<Integer,Employees>)request.getSession().getAttribute("empList");
       handleEmployeesListPageActions(request, response);
    }

   int selEmployeeId;
    private void handleEmployeesListPageActions(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServletException, IOException
    {
        
        if(request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME)!= null)
            selEmployeeId = Integer.parseInt(request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME));
        boolean DelButClicked = request.getParameter(DELETE_EMPLOYEE_BUTTON_NAME) != null;
        boolean EditButClicked = request.getParameter(EDIT_EMPLOYEE_BUTTON_NAME) != null;
        boolean AddButClicked =  request.getParameter(ADD_EMPLOYEE_BUTTON_NAME) != null;
         if(DelButClicked)
        {
            System.out.println("Delete button clicked " + request.getParameter(Constants.RADIO_EMPLOYEES_LIST_NAME));
            request.getSession().setAttribute("selEmployee", employees.get(selEmployeeId));
            try {
                LoginPageController.dba.deleteEmployee(employees.get(selEmployeeId));
                employees.remove(selEmployeeId);
                if((boolean)request.getSession().getAttribute("isAdmin"))
                        request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
                    else 
                        request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_EMP_PAGE).forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(EmployeesListPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(EditButClicked)
        {
            System.out.println("Edit/Details button clicked");
            Employees emp = employees.get(selEmployeeId);
            request.getSession().setAttribute("selEmployee", emp);
            request.getSession().setAttribute("previousPageUrl",Constants.JSP_EMPLOYEESLIST_PAGE);
            if((boolean)request.getSession().getAttribute("isAdmin"))
                request.getRequestDispatcher(Constants.JSP_EMPLOYEES_DETAILS_PAGE).forward(request, response);
            else 
                request.getRequestDispatcher(Constants.JSP_EMPLOYEES_DETAILS_EMP_PAGE).forward(request, response);
            return;
        }
        else if(AddButClicked)
        {
            request.setAttribute("selEmployee", null);
            System.out.println("Add button clicked");
            request.getRequestDispatcher(Constants.JSP_EMPLOYEES_DETAILS_PAGE).forward(request, response);
            return;
        }
        else 
        {
            System.out.println("Unknown button clicked");
        }
        
        // On revient sur la page des employes
        request.getSession().setAttribute("previousPageUrl",Constants.JSP_EMPLOYEESLIST_PAGE);
        request.getRequestDispatcher(Constants.JSP_EMPLOYEESLIST_PAGE).forward(request, response);
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
        processRequest(request, response);
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
