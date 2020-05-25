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
public class Menu {
    private int id;
    private String nom;
    private String image;
    private String description;
    private String jour_de_la_semaine;

    public Menu(int id, String nom, String description,String image ,String jour_de_la_semaine) {
        this.id = id;
        this.nom = nom;
        this.image=image;
        this.description = description;
        this.jour_de_la_semaine = jour_de_la_semaine;
    }

    public Menu() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJour_de_la_semaine() {
        return jour_de_la_semaine;
    }

    public void setJour_de_la_semaine(String jour_de_la_semaine) {
        this.jour_de_la_semaine = jour_de_la_semaine;
    }

    @Override
    public String toString() {
        return "Menu{" + "id=" + id + ", nom=" + nom + ", image=" + image + ", description=" + description + ", jour_de_la_semaine=" + jour_de_la_semaine + '}';
    }
    
           
    
}
