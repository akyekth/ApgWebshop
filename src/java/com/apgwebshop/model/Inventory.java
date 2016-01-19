/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apgwebshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aruna
 */
@Entity
@Table(name = "INVENTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i"),
    @NamedQuery(name = "Inventory.findById", query = "SELECT i FROM Inventory i WHERE i.id = :id"),
    @NamedQuery(name = "Inventory.findByArticleId", query = "SELECT i FROM Inventory i WHERE i.articleId = :articleId"),
    @NamedQuery(name = "Inventory.findByArticleName", query = "SELECT i FROM Inventory i WHERE i.articleName = :articleName"),
    @NamedQuery(name = "Inventory.findByArticleCount", query = "SELECT i FROM Inventory i WHERE i.articleCount = :articleCount")})
public class Inventory implements Serializable {

    @Column(name = "ARTICLE_COUNT")
    private Integer articleCount;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ARTICLE_PRICE")
    private BigDecimal articlePrice;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ARTICLE_ID")
    private String articleId;
    @Size(max = 200)
    @Column(name = "ARTICLE_NAME")
    private String articleName;

    public Inventory() {
    }

    public Inventory(Integer id) {
        this.id = id;
    }

    public Inventory(Integer id, String articleId, int articleCount) {
        this.id = id;
        this.articleId = articleId;
        this.articleCount = articleCount;
    }
    
    public Inventory(String articleId, String articleName, int articleCount) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.articleCount = articleCount;
    }
    
     public Inventory(String articleId, String articleName, BigDecimal articlePrice, int articleCount) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.articlePrice = articlePrice;
        this.articleCount = articleCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventory)) {
            return false;
        }
        Inventory other = (Inventory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apgwebshop.model.Inventory[ id=" + id + " ]";
    }
    
    public BigDecimal getArticlePrice() {
        return articlePrice;
    }

    public void setArticlePrice(BigDecimal articlePrice) {
        this.articlePrice = articlePrice;
    }
    
}
