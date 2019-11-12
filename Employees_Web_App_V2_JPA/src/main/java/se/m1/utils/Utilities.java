
package se.m1.utils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.model.Users;

public class Utilities {


    /**
     * Returns true if the role variable of the current user is set to "admin"
     * @param request
     * @return 
     */
    public static boolean CurUserIsAdmin(HttpServletRequest request) {
        return ((Users)request.getSession().getAttribute("userBean")).getRole().equals("admin");
    }

}
