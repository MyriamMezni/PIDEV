/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import com.mycompany.myapp.entities.Enfant;

/**
 *
 * @author ben younes
 */
public class EnfantComboBox {
    private Enfant e;
    public EnfantComboBox(Enfant e)
    {
        this.e=e;
    }
    public String toString()
    {
        return e.getPrenom()+" "+e.getNom();
    }

    public Enfant getE() {
        return e;
    }
    public String getNom() {
        return e.getNom();
    }

    public void setE(Enfant e) {
        this.e = e;
    }
    
}
