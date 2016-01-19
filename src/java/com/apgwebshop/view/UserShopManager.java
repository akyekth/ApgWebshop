/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apgwebshop.view;

import com.apgwebshop.controller.InventoryFacade;
import com.apgwebshop.controller.UserFacade;
import com.apgwebshop.controller.UserShopFacade;
import com.apgwebshop.dto.InventoryDTO;
import com.apgwebshop.model.Inventory;
import com.apgwebshop.model.User;
import com.apgwebshop.model.UserArticle;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.inject.Named;

/**
 *
 * @author aruna
 */
@Named("userShopManager")
@SessionScoped
public class UserShopManager implements Serializable {
    
    @EJB 
    private InventoryFacade inventoryFacade;
    
    @EJB 
    private UserShopFacade userShopFacade;
    
    @EJB
    private UserFacade userFacade;
    
    private String emptyInventoryMessage;
    
    private Boolean emptyInventory;
     
    private List<InventoryDTO> inventoryDTOList;
    
    private List<InventoryDTO> userArticleList;
    
    private String myCartMessage;
   
    private Boolean emptyCart;
    
    private String emptyCartMessage;
    
    private BigDecimal totalArticlesPrice;
    
    private String selectArtValMessage;
    
    private String articleName;
    
    private String emptySearchMessage;

    public String getEmptySearchMessage() {
        return emptySearchMessage;
    }

    public void setEmptySearchMessage(String emptySearchMessage) {
        this.emptySearchMessage = emptySearchMessage;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getSelectArtValMessage() {
        return selectArtValMessage;
    }

    public void setSelectArtValMessage(String selectArtValMessage) {
        this.selectArtValMessage = selectArtValMessage;
    }

    public BigDecimal getTotalArticlesPrice() {
        double totalArclePrice = 0.0;
        for(InventoryDTO art : userArticleList){
            totalArclePrice = totalArclePrice + art.getTotalPrice().doubleValue();
        }
        return new BigDecimal(totalArclePrice);
    }

    public void setTotalArticlesPrice(BigDecimal totalArticlesPrice) {
        this.totalArticlesPrice = totalArticlesPrice;
    }

     public Boolean getEmptyCart() {
        return userArticleList == null || userArticleList.isEmpty() ? Boolean.TRUE : Boolean.FALSE;
    }

    public void setEmptyCart(Boolean emptyCart) {
        this.emptyCart = emptyCart;
    }

    public String getEmptyCartMessage() {
        if(getEmptyCart()) {
            return "You haven't selected any articles. Go to Inventory Article page to select article";
        } 
        return "";
    }

    public void setEmptyCartMessage(String emptyCartMessage) {
        this.emptyCartMessage = emptyCartMessage;
    }
    
    public String getMyCartMessage() {
        return "My Cart (" +(userArticleList != null ? userArticleList.size() : 0)+")";
    }

    public void setMyCartMessage(String myCartMessage) {
        this.myCartMessage = myCartMessage;
    }

    public List<InventoryDTO> getUserArticleList() {
        return userArticleList;
    }

    public void setUserArticleList(List<InventoryDTO> userArticleList) {
        this.userArticleList = userArticleList;
    }

    public List<InventoryDTO> getInventoryDTOList() {
        return inventoryDTOList;
    }

    public void setInventoryDTOList(List<InventoryDTO> inventoryDTOList) {
        this.inventoryDTOList = inventoryDTOList;
    }

    public String getEmptyInventoryMessage() {
        return emptyInventoryMessage;
    }

    public void setEmptyInventoryMessage(String emptyInventoryMessage) {
        this.emptyInventoryMessage = emptyInventoryMessage;
    }

    public Boolean getEmptyInventory() {
        return emptyInventory;
    }

    public void setEmptyInventory(Boolean emptyInventory) {
        this.emptyInventory = emptyInventory;
    }   
    
    
    
    
    public String initShop(){
        
        populateInventoryDTOList();
        return "user_shop";
        
    }
    
    public void searchArticles(){
        
        List<Inventory> inventoryList = inventoryFacade.searchArticles(articleName);
          
        inventoryDTOList = new ArrayList<InventoryDTO>();
        if(inventoryList == null || inventoryList.isEmpty()){
            emptySearchMessage = "N articles found in Inventory with search !!!";
        } else {
            emptySearchMessage= "";
            for(Inventory inventory : inventoryList){
                 InventoryDTO inventoryDTO = new InventoryDTO(inventory.getArticleId(),
                        inventory.getArticleName(), inventory.getArticlePrice(),inventory.getArticleCount());            
                inventoryDTOList.add(inventoryDTO); 
            }
        }
    }

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
    
    public void addArticleToCart(InventoryDTO inventryDt){
        if(userArticleList == null){
            userArticleList = new ArrayList<InventoryDTO>();
        }
        System.out.println("com.apgwebshop.view.UserShopManager.addArticleToCart()"+inventryDt);
        if(inventryDt != null && inventryDt.getSelctdNoOfArticles() != null 
                &&inventryDt.getSelctdNoOfArticles() >0) {
            if(inventryDt.getSelctdNoOfArticles() > inventryDt.getArticleCount()) {
                 selectArtValMessage = "Selected articles count should be less than available count !!!";
            } else {
             userArticleList.add(inventryDt);
             selectArtValMessage = "";
            }
        } else {
            selectArtValMessage = "Enter atleast one article to add to Cart !!!";
        }
       
    }
    
    public void deleteFromCart(InventoryDTO inventryDt){
        userArticleList.remove(inventryDt);        
    }
    
    public String showSelectedArticles(){
        return "cart_main";
    }
    
    public String buyUserArticles(){
        if(userArticleList != null && !userArticleList.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();  
            javax.faces.application.Application app = context.getApplication();
            ValueBinding binding = app.createValueBinding("#{userManager1}"); 
            UserManager userManager = (UserManager) binding.getValue(context);
            
            User loginUser = null;
            if(userManager != null){
                String userId = userManager.getUserId();
                loginUser = userFacade.findUser(userId.trim());
            }
       
            for(InventoryDTO selectedArtcle : userArticleList) {
                
                UserArticle userArticle = new UserArticle(loginUser.getId(), selectedArtcle.getArticleName(),
                        selectedArtcle.getSelctdNoOfArticles(), selectedArtcle.getArticlePrice());
                
                userShopFacade.createUserArticle(userArticle);
                
                Inventory inventory = inventoryFacade.fetchInventory(selectedArtcle.getArticleId());
                
                inventory.setArticleCount(inventory.getArticleCount() - selectedArtcle.getSelctdNoOfArticles());
                
                inventoryFacade.updateInventory(inventory);
            }
        }
        userArticleList = new ArrayList<InventoryDTO>();
        populateInventoryDTOList();
        return "user_shop";
    }
    
    public String getMyOrders(){
        
        return "order_home";
    }
    
    
}
