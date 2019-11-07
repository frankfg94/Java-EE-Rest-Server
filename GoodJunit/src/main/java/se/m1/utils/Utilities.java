/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.utils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.m1.model.Users;

/**
 *
 * @author franc
 */
public class Utilities {

    public static String GetPrevPageName(HttpServletRequest request)
    {
           return  request.getHeader("Referer");
    }

    public static boolean CurUserIsAdmin(HttpServletRequest request) {
        return ((Users)request.getSession().getAttribute("userBean")).getRole().equals("admin");
    }

    public static void NavigateAndSavePrevPage(HttpServletRequest request, HttpServletResponse response, String prevPage) throws ServletException, IOException {
        request.getSession().setAttribute("previousPageUrl",prevPage);
        request.getRequestDispatcher(prevPage).forward(request, response);
    }
    
}
