/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.m1.beans;

import java.util.List;
import java.util.TreeMap;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import se.m1.model.Employees;

/**
 *
 * @author franc
 */
@Stateless
public class UsersSB {

    @PersistenceContext
    EntityManager em;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List getAllUsers()
    {
        return em.createNamedQuery("Users.findAll").getResultList();
    }
    
    
}
