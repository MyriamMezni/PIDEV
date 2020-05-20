/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.entities;

/**
 *
 * @author hamza
 */
public class Evenement {
    private int id,capacite;
    private String nom,heure_d,description,date_evt,image,type,depart,destination,lieu;

    public Evenement() {
    }
    
    public Evenement(int id, int capacite, String nom, String heure_d, String description, String date_evt, String image, String type, String depart, String destination, String lieu) {
        this.id = id;
        this.capacite = capacite;
        this.nom = nom;
        this.heure_d = heure_d;
        this.description = description;
        this.date_evt = date_evt;
        this.image = image;
        this.type = type;
        this.depart = depart;
        this.destination = destination;
        this.lieu = lieu;
    }

    public Evenement(int capacite, String nom, String heure_d, String description, String date_evt, String image, String type, String depart, String destination, String lieu) {
        this.capacite = capacite;
        this.nom = nom;
        this.heure_d = heure_d;
        this.description = description;
        this.date_evt = date_evt;
        this.image = image;
        this.type = type;
        this.depart = depart;
        this.destination = destination;
        this.lieu = lieu;
    }

    public int getId() {
        return id;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getNom() {
        return nom;
    }

    public String getHeure_d() {
        return heure_d;
    }

    public String getDescription() {
        return description;
    }

    public String getDate_evt() {
        return date_evt;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public String getDepart() {
        return depart;
    }

    public String getDestination() {
        return destination;
    }

    public String getLieu() {
        return lieu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setHeure_d(String heure_d) {
        this.heure_d = heure_d;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate_evt(String date_evt) {
        this.date_evt = date_evt;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
       
        return "nom=" + nom + "";
       
    }
    
    
    
    
    
    

 
   
}