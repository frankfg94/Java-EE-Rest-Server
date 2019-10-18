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
        <form action="EmployeesDetailsPageController">
            <%
                // Idée architecture françois : possibilité de parcourir chaque champ peut importe sa valeur et de l'afficher
                Employee emp = (Employee)request.getAttribute("selEmployee");                
                request.getSession().setAttribute("selEmployee", emp);

                      out.print("<h2>Details of Employee : " +  emp.getFirstname()+  " " + emp.getName()+ "</h2>");
                    out.print("<h3 style='display:inline-block; margin-right:10px'>Name</h3><input name='empName' maxlength='10' style='display:inline-block' type='text' value='"+emp.getName()+"'/>");
                    out.print("<br/><h3 style='display:inline-block; margin-right:10px'>First Name</h3><input name='empFirstName' maxlength='10' style='display:inline-block' type='text' value='"+emp.getFirstname()+"'/>");
                    out.print("<br/><h3 style='display:inline-block; margin-right:10px'>Home Phone</h3><input  name='empHomePhone' maxlength='10' style='display:inline-block' type='text' value='"+emp.getHomePhone()+"'/>");
                    out.print("<br/><h3 style='display:inline-block; margin-right:10px'>Mobile Phone</h3><input  name='empMobilePhone' maxlength='10' style='display:inline-block' type='text' value='"+emp.getMobilePhone()+"'/>");
                    out.print("<br/><h3 style='display:inline-block; margin-right:10px'>Work Phone</h3><input  name='empProPhone' maxlength='10' style='display:inline-block' type='text' value='"+emp.getProPhone()+"'/>");
                    out.print("<br/><h3 style='display:inline-block; margin-right:10px'>Address</h3><input  name='empGetAddress' maxlength='10' style='display:inline-block' type='text' value='"+emp.getAddress()+"'/>");
                    out.print("<br/><h3 style='display:inline-block; margin-right:10px'>Postal Code</h3><input  name='empPostalCode' maxlength='10' style='display:inline-block' type='text' value='"+emp.getPostalCode()+"'/>");
                    out.print("<br/><h3 style='display:inline-block; margin-right:10px'>City</h3><input  name='empCity' maxlength='10' style='display:inline-block' type='text' value='"+emp.getCity()+"'/>");
                    out.print("<br/><h3 style='display:inline-block; margin-right:10px'>Email</h3><input  name='empMail' maxlength='10'  style='display:inline-block' type='text' value='"+emp.getMail()+"'/>");
                out.print("<br/>");
                out.print("<input type='submit' value='Go Back' name='"+Constants.CANCEL_EMP_DETAILS_BUT_NAME+"'/>");
            %>
        </form>
       
    </body>
</html>
