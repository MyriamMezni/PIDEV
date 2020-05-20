/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.entities;

/**
 *
 * @author ASUS
 */
public class activite {
    private int IdActivite;
    private String Intitule;
    private String Niveau;
    private String Responsable;
    private String Type;
    private String HeureDebut;
    private String HeureFin;

    public activite() {
    }

    public activite(int IdActivite, String Intitule, String Niveau, String Responsable, String Type, String HeureDebut, String HeureFin) {
        this.IdActivite = IdActivite;
        this.Intitule = Intitule;
        this.Niveau = Niveau;
        this.Responsable = Responsable;
        this.Type = Type;
        this.HeureDebut = HeureDebut;
        this.HeureFin = HeureFin;
    }
    
    
      public activite( String Intitule, String Niveau, String Responsable, String Type, String HeureDebut, String HeureFin) {
        this.Intitule = Intitule;
        this.Niveau = Niveau;
        this.Responsable = Responsable;
        this.Type = Type;
        this.HeureDebut = HeureDebut;
        this.HeureFin = HeureFin;
    }

    public int getIdActivite() {
        return IdActivite;
    }

    public String getIntitule() {
        return Intitule;
    }

    public String getNiveau() {
        return Niveau;
    }

    public String getResponsable() {
        return Responsable;
    }

    public String getType() {
        return Type;
    }

    public String getHeureDebut() {
        return HeureDebut;
    }

    public String getHeureFin() {
        return HeureFin;
    }

    public void setIdActivite(int IdActivite) {
        this.IdActivite = IdActivite;
    }

    public void setIntitule(String Intitule) {
        this.Intitule = Intitule;
    }

    public void setNiveau(String Niveau) {
        this.Niveau = Niveau;
    }

    public void setResponsable(String Responsable) {
        this.Responsable = Responsable;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setHeureDebut(String HeureDebut) {
        this.HeureDebut = HeureDebut;
    }

    public void setHeureFin(String HeureFin) {
        this.HeureFin = HeureFin;
    }

     public String toString2() {
        return "activite{" + "IdActivite=" + IdActivite + ", Intitule=" + Intitule + ", Niveau=" + Niveau + ", Responsable=" + Responsable + ", Type=" + Type + ", HeureDebut=" + HeureDebut + ", HeureFin=" + HeureFin + '}';
    }

    @Override
    public String toString() {
        return Intitule + " : " + Niveau + " avec " + Responsable ;
        
    }
      
      
}
