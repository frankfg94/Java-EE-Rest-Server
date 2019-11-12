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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/employeesdetailpagestyle.css" media="screen">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page='Navbar.jsp'/>
        <div class="wrapper">
        <form class="form-signin" action="Controller" method="POST">
            <%
                // Idée architecture françois : possibilité de parcourir chaque champ peut importe sa valeur et de l'afficher
                Employee emp = (Employee)request.getAttribute("selEmployee");                
                request.getSession().setAttribute("selEmployee", emp);

                    out.print("<h1>Details of Employee : " +  emp.getFirstname()+  " " + emp.getName()+ "</h1><br/>");
                    out.print("<h3 style='display:inline-block; margin-right:10px'>Name</h3><input class='form-control' name='empName' maxlength='10' type='text' value='"+emp.getName()+"'/>");
                    out.print("<br/><h3>First Name</h3><input class='form-control' name='empFirstName' maxlength='10' type='text' value='"+emp.getFirstname()+"'/>");
                    out.print("<br/><h3>Home Phone</h3><input class='form-control'  name='empHomePhone' maxlength='10' type='text' value='"+emp.getHomePhone()+"'/>");
                    out.print("<br/><h3>Mobile Phone</h3><input class='form-control'  name='empMobilePhone' maxlength='10' type='text' value='"+emp.getMobilePhone()+"'/>");
                    out.print("<br/><h3>Work Phone</h3><input class='form-control'  name='empProPhone' maxlength='10' type='text' value='"+emp.getProPhone()+"'/>");
                    out.print("<br/><h3>Address</h3><input class='form-control' name='empGetAddress' maxlength='10' type='text' value='"+emp.getAddress()+"'/>");
                    out.print("<br/><h3>Postal Code</h3><input class='form-control' name='empPostalCode' maxlength='10' type='text' value='"+emp.getPostalCode()+"'/>");
                    out.print("<br/><h3>City</h3><input class='form-control' name='empCity' maxlength='10' type='text' value='"+emp.getCity()+"'/>");
                    out.print("<br/><h3>Email</h3><input class='form-control' name='empMail' maxlength='10' type='text' value='"+emp.getMail()+"'/>");
                out.print("<br/>");
                out.print("<input type='submit' class='btn btn-primary' value='Go Back' name='action'/>");
            %>
        </form>
       </div>
    </body>
</html>
