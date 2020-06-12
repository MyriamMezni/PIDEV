/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author ASUS
 */
public class Evenements {
   protected int id;
   protected String capacite;
   protected String nom;
   protected String heure_d ;
   
   protected String  description;
    protected String date_evt ;
    protected String image;
    protected String type;

    public Evenements(int id, String capacite, String nom, String heure_d,  String description, String date_evt, String image,String type) {
        this.id = id;
        this.capacite = capacite;
        this.nom = nom;
        this.heure_d = heure_d;
        this.description = description;
        this.date_evt = date_evt;
        this.image = image;
        this.type=type;
    }

   
     public Evenements( String capacite, String nom, String heure_d, String description, String date_evt, String image,String type) {
        this.capacite = capacite;
        this.nom = nom;
        this.heure_d = heure_d;
        this.description = description;
        this.date_evt = date_evt;
        this.image = image;
        this.type=type;
    }
    
    public Evenements() {
    }

    public String getCapacite() {
        return capacite;
    }



    public String getDescription() {
        return description;
    }

    public String getDate_evt() {
        return date_evt;
    }

    public String getHeure_d() {
        return heure_d;
    }

   

  

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate_evt(String date_evt) {
        this.date_evt = date_evt;
    }

    public void setHeure_d(String heure_d) {
        this.heure_d = heure_d;
    }

 
   

  
    public void setDescription(String description) {
        this.description = description;
    }

   

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Evenements{" + "id=" + id + ", capacite=" + capacite + ", nom=" + nom + ", heure_d=" + heure_d +   ", description=" + description + ", date_evt=" + date_evt + ", image=" + image + ", type=" + type + '}';
    }
    

    
    
    
    
}   

    


