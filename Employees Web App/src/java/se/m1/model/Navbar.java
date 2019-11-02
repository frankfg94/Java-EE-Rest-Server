
package se.m1.model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.utils.Constants;

public class Navbar{
     public void manageAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
     {
         if(request.getParameter("action").equals("Go to the Login Page"))
                request.getRequestDispatcher(Constants.JSP_LOGIN_PAGE).forward(request, response);
        else if(request.getParameter("action").equals("Disconnect"))
             request.getRequestDispatcher(Constants.JSP_GOODBYE_PAGE).forward(request, response);
     }
}
