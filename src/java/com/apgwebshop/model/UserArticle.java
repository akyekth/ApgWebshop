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
@Table(name = "USER_ARTICLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserArticle.findAll", query = "SELECT u FROM UserArticle u"),
    @NamedQuery(name = "UserArticle.findById", query = "SELECT u FROM UserArticle u WHERE u.id = :id"),
    @NamedQuery(name = "UserArticle.findByUserId", query = "SELECT u FROM UserArticle u WHERE u.userId = :userId"),
    @NamedQuery(name = "UserArticle.findByArticleName", query = "SELECT u FROM UserArticle u WHERE u.articleName = :articleName"),
    @NamedQuery(name = "UserArticle.findByArticleCount", query = "SELECT u FROM UserArticle u WHERE u.articleCount = :articleCount"),
    @NamedQuery(name = "UserArticle.findByArticlePrice", query = "SELECT u FROM UserArticle u WHERE u.articlePrice = :articlePrice")})
public class UserArticle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ARTICLE_NAME")
    private String articleName;
    @Column(name = "ARTICLE_COUNT")
    private Integer articleCount;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARTICLE_PRICE")
    private BigDecimal articlePrice;

    public UserArticle() {
    }

    public UserArticle(Integer id) {
        this.id = id;
    }

    public UserArticle(Integer id, int userId, String articleName, BigDecimal articlePrice) {
        this.id = id;
        this.userId = userId;
        this.articleName = articleName;
        this.articlePrice = articlePrice;
    }
    
     public UserArticle(int userId, String articleName, int articleCount, BigDecimal articlePrice) {
        this.userId = userId;
        this.articleName = articleName;
        this.articleCount = articleCount;
        this.articlePrice = articlePrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public BigDecimal getArticlePrice() {
        return articlePrice;
    }

    public void setArticlePrice(BigDecimal articlePrice) {
        this.articlePrice = articlePrice;
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
        if (!(object instanceof UserArticle)) {
            return false;
        }
        UserArticle other = (UserArticle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apgwebshop.model.UserArticle[ id=" + id + " ]";
    }
    
}
