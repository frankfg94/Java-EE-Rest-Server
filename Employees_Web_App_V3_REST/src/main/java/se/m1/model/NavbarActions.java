/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.utils.Constants;

/**
 *
 * @author franc
 */
public class NavbarActions {
     public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
     {
         
//         request.getSession().setAttribute("previousPageUrl", Constants.JSP_NAVBAR_PAGE);
        
         String clickedValue = (String)request.getParameter("action");

         if(clickedValue.equals("Disconnect"))
                request.getRequestDispatcher(Constants.JSP_GOODBYE_PAGE).forward(request, response);
         else if (clickedValue.equals("Go to the Login Page"))
                request.getRequestDispatcher(Constants.JSP_LOGIN_PAGE).forward(request, response);
     }

    
}
