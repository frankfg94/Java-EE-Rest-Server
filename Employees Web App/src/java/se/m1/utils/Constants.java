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


    public static final String QUERY_SEL_CREDENTIALS = "SELECT * from CREDENTIALS";
    public static final String QUERY_SEL_EMPLOYEES = "SELECT * from EMPLOYEES";
    public static final String ERR_MESSAGE = "Invalid credentials!";
    public static final String JSP_LOGIN_PAGE = "WEB-INF\\LoginPage.jsp";
    public static final String JSP_EMPLOYEESLIST_PAGE = "WEB-INF\\EmployeesListPage.jsp";
    public static final String JSP_HOME_PAGE = "WEB-INF/LoginPage.jsp";
    public static final String JSP_WELCOME_PAGE = "WEB-INF/EmployeesListPage.jsp";
    public static final String FRM_LOGIN_FIELD = "loginField";
    public static final String FRM_PWD_FIELD = "pwdField";
    public static final String LOGIN_FIELD_EMPTY_MSG = "Veuilez remplir l'identifiant de connexion";
    public static final String PWD_FIELD_EMPTY_MSG = "Veuillez entrer un mot de passe";
    
    // Employees list file
    public static final String RADIO_EMPLOYEES_LIST_NAME = "selRadioBut";
    public static final String JSP_EMPLOYEES_DETAILS_PAGE = "WEB-INF\\EmployeesDetailsPage.jsp";
    public static final String SAVE_EMP_DETAILS_BUT_NAME = "saveEmpBut";
    public static final String CANCEL_EMP_DETAILS_BUT_NAME = "cancelDetailsEmpBut";
    public static String CREATE_EMP_BUT_NAME = "createEmpBut";

}