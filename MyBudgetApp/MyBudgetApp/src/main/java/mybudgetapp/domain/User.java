/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

/**
 *
 * @author ralahtin
 */
public class User {
private String name;
private String username;

public User(String username, String name){
    this.name = name;
    this.username = username;
}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
   
    public String getUsername() {
        return username;
    } public void setUsername(){
        this.username = username;
    }

     @Override
    public boolean equals(Object obj){
        if(! (obj instanceof User)){
        return false;
    }
        User other = (User) obj;
        return username.equals(other.username);
    }
}

