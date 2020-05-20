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
public class CommandeStat {
    private Menu m;
    int nbr;
    
    public CommandeStat() {
    }

    public CommandeStat(Menu m, int nbr) {
        this.m = m;
        this.nbr = nbr;
    }

    public Menu getM() {
        return m;
    }

    public void setM(Menu m) {
        this.m = m;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    @Override
    public String toString() {
        return "CommandeStat{" + "m=" + m + ", nbr=" + nbr + '}';
    }
    
    
    
    
}
