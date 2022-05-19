/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Holder;

import Model.User;

/**
 *
 * @author Admin
 */
public final class UserHolder {
    private User user;
    private final static UserHolder INSTANCE = new UserHolder();
    private UserHolder() {}
  
    public static UserHolder getInstance() {
        return INSTANCE;
    }
    public void setUser(User u) {
        this.user = u;
    }
  
    public User getUser() {
        return this.user;
    }
}
