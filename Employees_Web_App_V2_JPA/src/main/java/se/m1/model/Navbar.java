
package se.m1.model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.utils.Constants;


public class Navbar {

    public void Log(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constants.JSP_LOGIN_PAGE).forward(request, response);
    }

    public void Disonnection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constants.JSP_GOODBYE_PAGE).forward(request, response);
    }

    
}
