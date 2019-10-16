<%-- 
    Document   : welcome
    Created on : 20 sept. 2019, 10:33:43
    Author     : JAA
--%>

<%@page import="se.m1.utils.Constants"%>
<!-- Valable uniquement pour la V1 du rendu (car pas le droit au code dans les JSPs) -->

<%@page import="se.m1.model.Employee"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="se.m1.model.DBActions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="se.m1.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="EmployeesListPageController">
            <%
                
                // Idée architecture françois : possibilité de parcourir chaque champ peut importe sa valeur et de l'afficher
                Employee emp = (Employee)request.getAttribute("selEmployee");
                out.print("<h2>Details of Employee : " +  emp.getFirstname()+  " " + emp.getName()+ "</h2>");
                    out.print("<h3>Name</h3><input type='text' value="+emp.getName()+"/>");
                    out.print("<h3>First Name</h3><input type='text' value="+emp.getFirstname()+"/>");
                    out.print("<h3>Home Phone</h3><input type='text' value="+emp.getHomePhone()+"/>");
                    out.print("<h3>Mobile Phone</h3><input type='text' value="+emp.getMobilePhone()+"/>");
                    out.print("<h3>Work Phone</h3><input type='text' value="+emp.getProPhone()+"/>");
                    out.print("<h3>Address</h3><input type='text' value="+emp.getAddress()+"/>");
                    out.print("<h3>Postal Code</h3><input type='text' value="+emp.getPostalCode()+"/>");
                    out.print("<h3>City</h3><input type='text' value="+emp.getCity()+"/>");
                    out.print("<h3>Email</h3><input type='text' value="+emp.getMail()+"/>");
                out.print("<br/>");
                out.print("<input type='submit' value='Save' name='saveDetailsButton'/>");
                out.print("<input type='submit' value='Cancel' name='cancelButton'/>");

            %>
        </form>
       
    </body>
</html>
