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

public class LoginBean implements Serializable{
    
    private String username;
    private String password;

    public LoginBean() {
        this.username = "";
        this.password = "";
    }

    public LoginBean (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }    

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}