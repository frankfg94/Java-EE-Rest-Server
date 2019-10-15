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

    public static final String URL = "jdbc:derby://localhost:1527/JEEDB";
    public static final String USER = "jee";
    public static final String PWD = "jee";
    public static final String QUERY_SEL_CREDENTIALS = "SELECT * from CREDENTIALS";
    public static final String QUERY_SEL_EMPLOYEES = "SELECT * from EMPLOYEES";
    public static final String ERR_MESSAGE = "Invalid credentials!";
    public static final String JSP_HOME_PAGE = "WEB-INF/home.jsp";
    public static final String JSP_WELCOME_PAGE = "WEB-INF/welcome.jsp";
    public static final String FRM_LOGIN_FIELD = "loginField";
    public static final String FRM_PWD_FIELD = "pwdField";

}