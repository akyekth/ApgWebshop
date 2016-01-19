/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apgwebshop.controller;

import com.apgwebshop.model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author aruna
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class UserFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName = "converterjsfPU")
    private EntityManager em;
    
    
    public void createUser(User user) {
        em.persist(user);
    }
    
    public void updateUser(User user){
        em.merge(user);
    }
    
    public User findUser(String userId){
        User user = null;
        Query query = em.createQuery("SELECT usr FROM User usr WHERE usr.userId ='"+userId+"'");
        List<User> userList = (List<User>) query.getResultList();
        System.out.println("com.apgwebshop.controller.UserFacade.findUser()"+userList);
        if(userList != null && !userList.isEmpty()) {
            System.out.println("com.apgwebshop.controller.UserFacade.findUser()==userlist is not null");
            user =userList.get(0);
        }
        return user;
    }
    
    public List<User> fetchActiveUsers(){
        Query query = em.createQuery("SELECT usr FROM USER usr");
        return (List<User>) query.getResultList();
    }
    
    
}
