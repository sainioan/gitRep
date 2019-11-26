/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.domain;

import mybudgetapp.domain.User;

/**
 *
 * @author anniinasainio
 */
public class MyBudget {

    private int id;
    private String content;
    private boolean done;
    private User user;

    public MyBudget(int id, String content, boolean done, User user) {
        this.id = id;
        this.content = content;
        this.done = done;
        this.user = user;
    }

    public MyBudget(String content, User user) {
        this.content = content;
        this.user = user;
        this.done = false;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    } public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    } @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyBudget)) {
            return false;
        }
        MyBudget other = (MyBudget) obj;
        return id == other.id;
    }
}
