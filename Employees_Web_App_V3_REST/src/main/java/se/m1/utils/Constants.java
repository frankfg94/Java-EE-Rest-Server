/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.utils;

/**
 *
 * @author nitsu
 */
public class Constants {


    
    //Files
    public static final String FILE_PROPERTIES_DB_PATH = "/WEB-INF/db.properties";
    
    // JSPS
    public static final String QUERY_SEL_CREDENTIALS = "SELECT * from CREDENTIALS";
    public static final String QUERY_SEL_EMPLOYEES = "SELECT * from EMPLOYEES";
    public static final String ERR_MESSAGE = "Invalid credentials!";
    public static final String JSP_LOGIN_PAGE = "WEB-INF\\LoginPage.jsp";
    public static final String JSP_EMPLOYEESLIST_PAGE = "WEB-INF\\EmployeesListPage.jsp";
    public static final String JSP_EMPLOYEESLIST_EMP_PAGE = "WEB-INF\\EmployeesListPageEmp.jsp";
    public static final String JSP_HOME_PAGE = "WEB-INF/LoginPage.jsp";
    public static final String JSP_WELCOME_PAGE = "WEB-INF/EmployeesListPage.jsp";
    public static final String JSP_NAVBAR_PAGE= "WEB-INF\\Navbar.jsp";
    
    // Login Form
    public static final String FRM_LOGIN_FIELD_NAME = "loginField";
    public static final String FRM_PASS_FIELD_NAME = "pwdField";
    public static final String LOGIN_FIELD_EMPTY_MSG = "Veuilez remplir l'identifiant de connexion";
    public static final String PASS_FIELD_EMPTY_MSG = "Veuillez entrer un mot de passe";
    public static final String ERR_MSG = "errKey";

    
    // Employees general info
    public static final String SEL_EMPLOYEE_NAME = "selEmployee";
        
    // Employees list file
    public static final String RADIO_EMPLOYEES_LIST_NAME = "selRadioBut";
    public static final String JSP_EMPLOYEES_DETAILS_PAGE = "WEB-INF\\EmployeesDetailsPage.jsp";
    public static final String JSP_EMPLOYEES_DETAILS_EMP_PAGE = "WEB-INF\\EmployeesDetailsPageEmp.jsp";
    public static final String SAVE_EMP_DETAILS_BUT_NAME = "saveEmpBut";
    public static final String CANCEL_EMP_DETAILS_BUT_NAME = "cancelDetailsEmpBut";
    public static final String CREATE_EMP_BUT_NAME = "createEmpBut";
    public static final String JSP_GOODBYE_PAGE = "WEB-INF\\GoodbyePage.jsp";
    

}