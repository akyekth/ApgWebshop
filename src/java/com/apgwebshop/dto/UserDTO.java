/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apgwebshop.dto;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author aruna
 */
@Named("userDTO")
@SessionScoped
public class UserDTO implements Serializable {
    
    private String userId;

    private String userName;
    
    private String userRole;
    
    private String userStaus;    
   
    private Boolean userAvailable;
   
    public UserDTO(){
        
    }
    
    public UserDTO(String userId, String userName, String userRole, String userStatus){
        this.userId = userId;
        this.userName = userName;
        this.userRole = userRole;
        this.userStaus = userStatus;
        
        
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserStaus() {
        return userStaus;
    }

    public void setUserStaus(String userStaus) {
        this.userStaus = userStaus;
    }   

    public Boolean getUserAvailable() {
        return userAvailable;
    }

    public void setUserAvailable(Boolean userAvailable) {
        this.userAvailable = userAvailable;
    }

}
