/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author franc
 */
@XmlRootElement(name = "employeess")
public class EmployeesContainer {
    public ArrayList<Employees> employees ;
}
