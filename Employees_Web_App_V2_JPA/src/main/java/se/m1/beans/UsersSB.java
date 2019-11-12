package se.m1.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UsersSB {

    @PersistenceContext
    EntityManager em;

    /**
     * Get all the users that can connect to the login page
     * @return 
     */
    public List getAllUsers() {
        return em.createNamedQuery("Users.findAll").getResultList();
    }

}
