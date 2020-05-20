/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author ben younes
 */
public class AdminComboBox {
    private User u;

    public AdminComboBox(User u) {
        this.u = u;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    @Override
    public String toString() {
        if(u.getRole().equals("Admin"))
        {
            return "Admin: "+u.getUsername();
        }
        else
        {
            return "Employe: "+u.getUsername();
        }
    }
    
    
}
