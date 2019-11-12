/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.utils;

public class Constants {

    //Error Messages
    public static final String ERR_MESSAGE = "Invalid credentials!";
    public static final String PWD_FIELD_EMPTY_MSG = "Veuillez entrer un mot de passe"; 
    public static final String LOGIN_FIELD_EMPTY_MSG = "Veuilez remplir l'identifiant de connexion";
        
    //SQL Command
    public static final String QUERY_SEL_CREDENTIALS = "SELECT * from CREDENTIALS";
    public static final String QUERY_SEL_EMPLOYEES = "SELECT * from EMPLOYEES";
    
    //JSP Page
    public static final String JSP_LOGIN_PAGE = "WEB-INF\\LoginPage.jsp";
    public static final String JSP_EMPLOYEESLIST_PAGE = "WEB-INF\\EmployeesListPage.jsp";
    public static final String JSP_EMPLOYEESLIST_EMP_PAGE = "WEB-INF\\EmployeesListPageEmp.jsp";
    public static final String JSP_HOME_PAGE = "WEB-INF/LoginPage.jsp";
    public static final String JSP_WELCOME_PAGE = "WEB-INF/EmployeesListPage.jsp";
    
    //Login information
    public static final String FRM_LOGIN_FIELD = "loginField";
    public static final String FRM_PWD_FIELD = "pwdField";
    
    // Employees list file
    public static final String RADIO_EMPLOYEES_LIST_NAME = "selRadioBut";
    public static final String JSP_EMPLOYEES_DETAILS_PAGE = "WEB-INF\\EmployeesDetailsPage.jsp";
    public static final String JSP_EMPLOYEES_DETAILS_EMP_PAGE = "WEB-INF\\EmployeesDetailsPageEmp.jsp";
    public static final String JSP_GOODBYE_PAGE = "WEB-INF\\GoodbyePage.jsp";
}