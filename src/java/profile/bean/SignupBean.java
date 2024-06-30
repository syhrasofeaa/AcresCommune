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

public class SignupBean implements Serializable{
    
    private String username;
    private String password;
    private String email;
    private String name;

    public SignupBean () {
        this.username = "";
        this.password = "";
        this.email = "";
        this.name = "";
    }

    public SignupBean(String username, String password, String email, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
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