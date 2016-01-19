/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apgwebshop.view;

import com.apgwebshop.controller.UserFacade;
import com.apgwebshop.dto.UserDTO;
import com.apgwebshop.model.User;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author aruna
 * usermanager bean class and manages the user details
 
 */

@Named("userManager1")
@SessionScoped
public class UserManager implements Serializable{
    
    @EJB
    private UserFacade userFacade;
    
    private String userId;   
    
    private String password;
    
    private String confPassword;
    
    private String validMessage;
    
    private String seachUserId;
    
    private String existUser;
    
    private String userName;    
    
    //private Boolean userAvailable;
    
    private UserDTO userDTO;
     
     public UserManager(){
        this.seachUserId = "";
        this.existUser="";
        this.userId = "";
        this.password="";
        this.validMessage="";
        this.userDTO = new UserDTO();
    }  
    
    
     public String getExistUser() {
        return existUser;
    }

    public void setExistUser(String existUser) {
        this.existUser = existUser;
    }

    /*public Boolean getUserAvailable() {
        return userAvailable;
    }

    public void setUserAvailable(Boolean userAvailable) {
        this.userAvailable = userAvailable;
    }*/

    public String getSeachUserId() {
        return seachUserId;
    }

    public void setSeachUserId(String seachUserId) {
        this.seachUserId = seachUserId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getValidMessage() {
        return validMessage;
    }

    public void setValidMessage(String validMessage) {
        this.validMessage = validMessage;
    }
     public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
      
      /* 
        login method is checking valid user otherwise thrown error please register
    */
    
    public String login(){
        try {

            User user = userFacade.findUser(userId.trim());
            if(user == null) {
                validMessage = "User doesn't exist in System. Please register !!!";
                return "login";
            }
            if(user != null && password.trim().equals(user.getPassword().trim())) {
                 System.out.println("com.apgwebshop.view.UserManager.login()====pass"+user.getUserStatus());
                  System.out.println("com.apgwebshop.view.UserManager.login()====role"+user.getUserRole());
                 if(user.getUserRole().equals("ADMIN")) {
                     return "admin_home";
                 } else {
                     if("TRUE".equals(user.getUserStatus())) {
                        return "user_home";
                     } else {
                         validMessage = "User is inactive in System. Please contact Adminstator to activate user.";
                         return "login";
                     }
                     
                 }
               
            } else {
                 validMessage = "Invalid Password !!!";
                 return "login";
            }
        }
        catch(Exception exp){
            exp.printStackTrace();
            return "error";
        }
        
    }
    
    /*
    find()is find the user is registeruser or not
    return 
    */
    public void find(){
        
        try{
            User user = userFacade.findUser(seachUserId.trim());
            System.out.println("com.apgwebshop.view.UserManager.find()==="+user);
            if(user == null) {
                existUser = "User doesn't exist in System";
                if(userDTO == null) {
                    userDTO = new UserDTO();
                }
               // userAvailable = Boolean.FALSE;
                userDTO.setUserAvailable(Boolean.FALSE);
            } else {
                //userAvailable = Boolean.TRUE;
                existUser = "";
                userDTO = new UserDTO(user.getUserId(), user.getUserName(), user.getUserRole(), user.getUserStatus());;
                userDTO.setUserAvailable(Boolean.TRUE);
           }
        } catch(Exception exp) {
            exp.printStackTrace();
        }
    }
    
    public void deActivateUser(){        
         User user = userFacade.findUser(seachUserId.trim());
         if(user != null){
             System.out.println("com.apgwebshop.view.UserManager.deActivateUser()==="+user.getUserStatus());
             user.setUserStatus("FALSE");
             userFacade.updateUser(user);
             System.out.println("com.apgwebshop.view.UserManager.deActivateUser()===111"+user.getUserStatus());
             userDTO = new UserDTO(user.getUserId(), user.getUserName(), user.getUserRole(), user.getUserStatus());;
             
         }
    }
    public void activateUser(){
        User user = userFacade.findUser(seachUserId.trim());
         if(user != null){
             user.setUserStatus("TRUE");
             userFacade.updateUser(user);
             userDTO = new UserDTO(user.getUserId(), user.getUserName(), user.getUserRole(), user.getUserStatus());;

         }
    }
    
    public String searchUser(){
        this.userId = null;
        this.password = null;
        return "search_user";
    }
    
    public String registerUser(){
        this.validMessage = null;
        this.userId = null;
        this.password = null;
        this.userName = null;
        this.confPassword = null;
        return "newuser";
    }
    
    public String fecthInventory(){
        return "";
    }
    
    public String addUser(){
        try{
            validMessage = "";
            if(password.trim().equals(confPassword.trim())){
                User existingUser = userFacade.findUser(userId.trim());
                if(existingUser != null){
                    validMessage = "User already exists";
                    return "register_user";
                } else {
                    User user = new User(userId.trim(), userName, password.trim(), "USER", "TRUE");
                    userFacade.createUser(user);
                    userId=""; 
                    password="";
                    return "login";
                }
                
            } else {
                validMessage = "Password must be match with Confirm Password";
                return "register_user";
            }           
        }
        catch(Exception exp) {
            exp.printStackTrace();
            return "error";
        }
    }
    
    
    
   
    
}
