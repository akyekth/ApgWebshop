/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apgwebshop.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author aruna
 */
@Named("inventoryDT")
@SessionScoped
public class InventoryDTO implements Serializable {
    
    
    private String articleId;    
    
    private String articleName;
    
    private Integer articleCount;
    
    private BigDecimal articlePrice;
    
    private BigDecimal totalPrice;
    
    private Boolean noStock;

    public Boolean getNoStock() {
        return articleCount < 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    public void setNoStock(Boolean noStock) {
        this.noStock = noStock;
    }

    public BigDecimal getTotalPrice() {
        return new BigDecimal(selctdNoOfArticles*articlePrice.doubleValue());
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getArticlePrice() {
        return articlePrice;
    }

    public void setArticlePrice(BigDecimal articlePrice) {
        this.articlePrice = articlePrice;
    }
    
    private Integer selctdNoOfArticles;

    public Integer getSelctdNoOfArticles() {
        return selctdNoOfArticles;
    }

    public void setSelctdNoOfArticles(Integer selctdNoOfArticles) {
        this.selctdNoOfArticles = selctdNoOfArticles;
    }
    
    public InventoryDTO(){
        
    }
    
    public InventoryDTO(String articleId, String articleNm, Integer articleCount){
         this.articleId = articleId;
         this.articleName = articleNm;
         this.articleCount = articleCount;
        
    }
    
     public InventoryDTO(String articleId, String articleNm, BigDecimal articlePrice, Integer articleCount){
         this.articleId = articleId;
         this.articleName = articleNm;
         this.articlePrice = articlePrice;
         this.articleCount = articleCount;
        
    }
    
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }
}
