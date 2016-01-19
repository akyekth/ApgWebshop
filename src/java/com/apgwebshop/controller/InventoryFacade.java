/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apgwebshop.controller;

import com.apgwebshop.model.Inventory;
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
public class InventoryFacade {
    
    @PersistenceContext(unitName = "converterjsfPU")
    private EntityManager em;  
    
    
    public List<Inventory> fetchInventoryList(){
       Query query = em.createQuery("SELECT inventory FROM Inventory inventory");
       return  query.getResultList();
    }
    
     public Inventory fetchInventory(String articleId){
        Inventory inventory = null;
        List<Inventory>  inventoryList = em.createQuery("SELECT inventory FROM Inventory inventory WHERE inventory.articleId = '"+articleId+"'").getResultList();
        if(inventoryList != null && !inventoryList.isEmpty()){
            inventory = inventoryList.get(0);
        }
        return inventory;
    }
    
    public void createInventory(Inventory inventory){
        em.persist(inventory);
    }
    
    public void updateInventory(Inventory inventory){
        em.merge(inventory);   
        em.flush();
    }
    
    public void deleteInventory(Inventory inventory){
        Inventory inv= em.find(Inventory.class, inventory.getId());   
        em.remove(inv);
    }

    public List<Inventory> searchArticles(String articleName) {
        return em.createQuery("SELECT inventory FROM Inventory inventory WHERE UPPER(inventory.articleName)  LIKE UPPER ('%"+articleName+"%')").getResultList();
    }
    
}
