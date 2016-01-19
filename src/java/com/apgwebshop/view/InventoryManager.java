/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apgwebshop.view;

import com.apgwebshop.dto.InventoryDTO;
import com.apgwebshop.controller.InventoryFacade;
import com.apgwebshop.model.Inventory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author aruna
 * 
 * @ Inventorymanager class maintains addinventory,updateinventory
 * @fetching inventory list and populated inventory 
 * @addarticle to inventory,delete article from inventory
 */
@Named("inventoryManager")
@SessionScoped

public class InventoryManager implements Serializable {
    
    
    @EJB 
    private InventoryFacade inventoryFacade;
    
    private List<InventoryDTO> inventoryDTOList;
    private String emptyInventoryMessage;
    private Boolean emptyInventory;
    private InventoryDTO inventoryDT;
    private String validationMessage;

    public InventoryDTO getInventoryDT() {
        return inventoryDT;
    }

    public void setInventoryDT(InventoryDTO inventoryDT) {
        this.inventoryDT = inventoryDT;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public Boolean getEmptyInventory() {
        return emptyInventory;
    }

    public void setEmptyInventory(Boolean emptyInventory) {
        this.emptyInventory = emptyInventory;
    }

    public String getEmptyInventoryMessage() {
        return emptyInventoryMessage;
    }

    public void setEmptyInventoryMessage(String emptyInventoryMessage) {
        this.emptyInventoryMessage = emptyInventoryMessage;
    }

    public List<InventoryDTO> getInventoryDTOList() {
        return inventoryDTOList;
    }

    public void setInventoryDTOList(List<InventoryDTO> inventoryDTOList) {
        this.inventoryDTOList = inventoryDTOList;
    }
    
    /*
      Fetch the inventory from database 
      @return inventory_mnage page
    */
    public String fetchInventoryList(){
        
        populateInventoryDTOList();
        return "inventory_manage";
        
    }
    /*
       When article is deleted and new article is added its populate inventorylist
     * @populatedinventorylist and add that list to InventoryDTO
    */
    private void populateInventoryDTOList() {
        List<Inventory> inventoryList = inventoryFacade.fetchInventoryList();
        System.out.println("com.apgwebshop.view.InventoryManager.populateInventoryDTOList()"+inventoryList);
        inventoryDTOList = new ArrayList<InventoryDTO>();
        if(inventoryList == null || inventoryList.isEmpty()){
            emptyInventory = Boolean.TRUE;
            emptyInventoryMessage = "There are no articles in Inventory !!!";
        } else {
            emptyInventory = Boolean.FALSE;
            for(Inventory inventory : inventoryList){
                InventoryDTO inventoryDTO = new InventoryDTO(inventory.getArticleId(),
                        inventory.getArticleName(), inventory.getArticlePrice(),inventory.getArticleCount());            
                inventoryDTOList.add(inventoryDTO);
            }
        }
    }
    
    /*
       Add article into the system and and return message  add_artical page.
    */
    public String addArticle(){
        Inventory inventory = inventoryFacade.fetchInventory(inventoryDT.getArticleId());
        if(inventory == null){
            inventory = new Inventory(inventoryDT.getArticleId(), inventoryDT.getArticleName(),
                    inventoryDT.getArticlePrice(),inventoryDT.getArticleCount());;
            inventoryFacade.createInventory(inventory);
             populateInventoryDTOList();
            return "inventory_manage";
        } else {
            validationMessage = "Article already exists in system !!!";
            return "add_article";
        }
       
    }
    
    /*
        when new Article is added and it return to addnewarticle jsf page
    */
    public String addNewArticle(){
        inventoryDT = new InventoryDTO();
        return "addNewArticle";
    }
    
    /*
     this method is called when updating item number in inventory
    */

    public void updateInventory(InventoryDTO inventoryDTO){
        
        Inventory inventory = inventoryFacade.fetchInventory(inventoryDTO.getArticleId());
        if(inventory != null) {
            inventory.setArticleCount(inventoryDTO.getArticleCount());
            inventory.setArticlePrice(inventoryDTO.getArticlePrice());
            inventoryFacade.updateInventory(inventory);
        }        
        populateInventoryDTOList();
        
    }
    
    /*
       removeinventory method is called when administrator 
       removing item from inventory
    */
    public void removeInventory(InventoryDTO inventoryDTO){
        
        Inventory inventory = inventoryFacade.fetchInventory(inventoryDTO.getArticleId());
        if(inventory != null) {
            inventoryFacade.deleteInventory(inventory);
        }
        populateInventoryDTOList();
    }
    
}
