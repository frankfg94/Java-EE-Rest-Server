
package se.m1.model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.utils.Constants;


public class Navbar {

    /**
     * Redirects the client to the Login Page
     * @param request the servlet request
     * @param response the servlet response
     * @throws javax.servlet.ServletException if the servlet has a bug, throw it
     * @throws java.io.IOException if a file/link is not correctly parsed
     */
    public void Log(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constants.JSP_LOGIN_PAGE).forward(request, response);
    }

    /**
     * Redirect the current user to the disconnection page
     * @param request the servlet request
     * @param response the servlet response
     * @throws javax.servlet.ServletException if the servlet has a bug, throw it
     * @throws java.io.IOException if a file/link is not correctly parsed
     */
    public void Disconnection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constants.JSP_GOODBYE_PAGE).forward(request, response);
    }

    
}
