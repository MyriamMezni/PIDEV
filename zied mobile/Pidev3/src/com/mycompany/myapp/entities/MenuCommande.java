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
public class MenuCommande {
    private int idEnfant;
    private Menu idMenu;
    private String jourDeLaSemaine;

    public MenuCommande(int idEnfant, Menu idMenu, String jourDeLaSemaine) {
        this.idEnfant = idEnfant;
        this.idMenu = idMenu;
        this.jourDeLaSemaine = jourDeLaSemaine;
    }

    public MenuCommande() {
    }
    
    public int getIdEnfant() {
        return idEnfant;
    }

    public void setIdEnfant(int idEnfant) {
        this.idEnfant = idEnfant;
    }

    public Menu getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Menu idMenu) {
        this.idMenu = idMenu;
    }

    public String getJourDeLaSemaine() {
        return jourDeLaSemaine;
    }

    public void setJourDeLaSemaine(String jourDeLaSemaine) {
        this.jourDeLaSemaine = jourDeLaSemaine;
    }

    @Override
    public String toString() {
        return "MenuCommande{" + "idEnfant=" + idEnfant + ", idMenu=" + idMenu + ", jourDeLaSemaine=" + jourDeLaSemaine + '}';
    }
    
    
    
    
}
