<%-- 
    Document   : welcome
    Created on : 20 sept. 2019, 10:33:43
    Author     : JAA
--%>

<%@page import="se.m1.Employee"%>
<%@page import="org.apache.derby.client.am.SqlException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="se.m1.model.DBActions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="se.m1.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>  hi</h1>

        <%
            ArrayList<Employee> employees = (ArrayList<Employee>)session.getAttribute("eList");
            out.print("<table border='1'>");
            for(Employee emp : employees)
           {
               out.print("<tr>");
               out.print("<td>"+emp.getName() +" "+ emp.getFirstName()+"</td>");   
               out.print("<td>"+emp.getEmail()+"</td>");
               out.print("<td>"+emp.getPostalCode()+"</td>");
               out.print("<td>"+emp.getTelPro()+"</td>");
               out.print("<td>"+emp.getTelMob()+"</td>");
               out.print("</tr>");
           }
            out.print("</table>");
            %>


    </body>
</html>
