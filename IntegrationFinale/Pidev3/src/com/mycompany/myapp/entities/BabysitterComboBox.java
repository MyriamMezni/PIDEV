/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author HEDI MSELMI
 */
public class BabysitterComboBox {
    private User u;

    public BabysitterComboBox(User u) {
        this.u = u;
    }

    public User getU() {
        return u;
    }
     public String getUsername() {
        return u.getUsername();
    }

    public void setU(User u) {
        this.u = u;
    }

    @Override
    public String toString() {
        
            return ""+u.getUsername();
        
       
    }
}
