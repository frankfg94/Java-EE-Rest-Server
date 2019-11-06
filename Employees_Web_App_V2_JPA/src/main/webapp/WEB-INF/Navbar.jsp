<%-- 
    Document   : Navbar
    Created on : 18 oct. 2019, 15:58:04
    Author     : franc
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="se.m1.utils.Constants"%>
<!DOCTYPE html>
        <form action="Controller">
            <div>
                <div class="disconnect-div" >
                    <h2>Hello ${sessionScope.userBean.login} your session is Active! </h2>
                    <button type="submit" name="action" value="Disconnect" >
                        <img width="25" height="25" src="https://cdn.pixabay.com/photo/2017/01/02/21/42/power-off-1947949_960_720.png" alt="Disconnect">
                    </button>
                 </div>
             </div>
        </form>
     
