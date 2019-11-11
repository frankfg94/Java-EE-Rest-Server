<%-- 
    Document   : Navbar
    Created on : 18 oct. 2019, 15:58:04
    Author     : franc
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="se.m1.utils.Constants"%>
  <head lang="en">
    <!-- Place your kit's code here -->
    <script src="https://kit.fontawesome.com/a797cb5608.js" crossorigin="anonymous"></script>
  </head>
<!DOCTYPE html>
        <form action="Controller">
            <div>
                <div class="error-div bottom" >
                    <i class="fas fa-exclamation-triangle"></i>
                    <h2>An error has occured</h2>
                    <p>${sessionScope.errMsg}</p>
                 </div>
             </div>
        </form>
     
