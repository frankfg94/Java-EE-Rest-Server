<%-- 
    Document   : Navbar
    Created on : 18 oct. 2019, 15:58:04
    Author     : franc
--%>

<%@page import="se.m1.utils.Constants"%>
<!DOCTYPE html>

<nav class="navbar navbar-light bg-light fixed-top">
    <%
        String s = (String) request.getSession().getAttribute("username");
        out.print("<a>Hello " + s + " your session is Active! </a>");
    %> 
    <form action="Controller" method="GET">
        <input type="submit" class="btn btn-outline-secondary" name="action" value="Disconnect">
    </form>

</nav>

<%--

        <form action="Controller">
            <div>
                <div class="disconnect-div">
                     <% 
                         String s=  (String)request.getSession().getAttribute("username");
                         out.print("<h2>Hello "+ s+" your session is Active! </h2>");
                     %> 
                    <button type="submit" name="action" value="Disconnect" >
                        <img width="25" height="25" src="https://cdn.pixabay.com/photo/2017/01/02/21/42/power-off-1947949_960_720.png" alt="Disconnect">
                 </button>
                 </div>
             </div>
        </form>
--%>
