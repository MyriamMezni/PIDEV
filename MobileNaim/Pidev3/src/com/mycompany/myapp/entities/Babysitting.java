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
public class Babysitting {
    int idBabysitter;
    int heureDebut;
    int heureFin;
    String jourSemaine;
    int prixHeure;
    Enfant id_enfant;
    User s;

    public Babysitting(int idBabysitter, int heureDebut, int heureFin, String jourSemaine, int prixHeure, Enfant id_enfant) {
        this.idBabysitter = idBabysitter;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.jourSemaine = jourSemaine;
        this.prixHeure = prixHeure;
        this.id_enfant = id_enfant;
    }

    public Babysitting() {
        //To change body of generated methods, choose Tools | Templates.
    }

    public  int  getIdBabysitter() {
        return idBabysitter;
    }

    public void setIdBabysitter(int idBabysitter) {
        this.idBabysitter = idBabysitter;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(int heureFin) {
        this.heureFin = heureFin;
    }

    public String getJourSemaine() {
        return jourSemaine;
    }

    public void setJourSemaine(String jourSemaine) {
        this.jourSemaine = jourSemaine;
    }

    public float getPrixHeure() {
        return prixHeure;
    }

    public void setPrixHeure(int prixHeure) {
        this.prixHeure = prixHeure;
    }

    public Enfant getId_enfant() {
        return id_enfant;
    }

    public void setId_enfant(Enfant id_enfant) {
        this.id_enfant = id_enfant;
    }

    

   

   

   

    

    @Override
    public String toString() {
    
        return "idBabysitter=" + idBabysitter + "Babysitting{" + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", jourSemaine=" + jourSemaine + ", prixHeure=" + prixHeure + ", id_enfant=" + id_enfant + '}';
    }

    public int affiche() {
       // int IdUser=s.getIdUser();
             
        return idBabysitter;
    }

    

  

    

  

    

    
}
