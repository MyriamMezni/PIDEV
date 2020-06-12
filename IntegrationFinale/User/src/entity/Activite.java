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
public class Activite {
    private int IdActivite;
    private String Intitule;
    private String Niveau;
    private String Responsable;
    private String Type;
    private String HeureDebut;
    private String HeureFin;

    public Activite() {
    }
   public Activite( String Intitule, String Niveau, String Responsable, String Type, String HeureDebut, String HeureFin) {
        this.Intitule = Intitule;
        this.Niveau = Niveau;
        this.Responsable = Responsable;
        this.Type = Type;
        this.HeureDebut = HeureDebut;
        this.HeureFin = HeureFin;
    }
    
    public Activite(int IdActivite, String Intitule, String Niveau, String Responsable, String Type, String HeureDebut, String HeureFin) {
        this.IdActivite = IdActivite;
        this.Intitule = Intitule;
        this.Niveau = Niveau;
        this.Responsable = Responsable;
        this.Type = Type;
        this.HeureDebut = HeureDebut;
        this.HeureFin = HeureFin;
    }

    public String getHeureDebut() {
        return HeureDebut;
    }

    

    

  

    public String getHeureFin() {
        return HeureFin;
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

    public void setHeureDebut(String HeureDebut) {
        this.HeureDebut = HeureDebut;
    }

    public void setHeureFin(String HeureFin) {
        this.HeureFin = HeureFin;
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

    @Override
    public String toString() {
        return "Activite{" + "IdActivite=" + IdActivite + ", Intitule=" + Intitule + ", Niveau=" + Niveau + ", Responsable=" + Responsable + ", Type=" + Type + ", HeureDebut=" + HeureDebut + ", HeureFin=" + HeureFin + '}';
    }

  
   


}
