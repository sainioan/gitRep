/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybudgetapp.dao;

import java.util.ArrayList;
import java.util.List;
import mybudgetapp.domain.User;

/**
 *
 * @author anniinasainio
 */
public class DBUserDao implements UserDao {

    @Override
    public User create(User user) throws Exception {
        return user;
    }

    @Override
    public User findByUsername(String username) {
        String password = "";
        User theUser = new User(username, password);
        return theUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        return users;
    }
}
