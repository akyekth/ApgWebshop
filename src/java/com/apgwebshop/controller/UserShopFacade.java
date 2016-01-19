/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apgwebshop.controller;

import com.apgwebshop.model.UserArticle;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aruna
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class UserShopFacade {

  @PersistenceContext(unitName = "converterjsfPU")
  private EntityManager em;
  
  public List<UserArticle> fetchUserArticles(){
      return em.createQuery("SELECT userArticle FROM UserArticle userArticle").getResultList();
  }
  
  public void createUserArticle(UserArticle userArticle){
      em.persist(userArticle);
  }
  
  
}
