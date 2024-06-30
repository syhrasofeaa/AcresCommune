/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profile.bean;

/**
 *
 * @author syahira sofea
 */

import java.io.Serializable;

public class ProfileBean implements Serializable{
    
    private String farmerID;
    private String username;
    private String password;
    private String email;
    private String name;

    public ProfileBean () {
        this.farmerID = "";
        this.username = "";
        this.password = "";
        this.email = "";
        this.name = "";
    }

    public ProfileBean(String username, String password, String email, String name, String farmerID) {
        this.farmerID = farmerID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public String getFarmerID() {
        return farmerID;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }    

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setFarmerID(String farmerID) {
        this.farmerID = farmerID;
    }
    

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}