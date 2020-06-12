/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author ASUS
 */
public class Programme {
     private int IdProgramme;
     private String Intitule;

     private String Cours;
     private String Debut;
     private String Fin;

    public Programme() {
    }
    
      public Programme( String Intitule,String Cours, String Debut, String Fin) {
        this.Intitule=Intitule;
          this.Cours = Cours;
        this.Debut = Debut;
        this.Fin = Fin;
    }

    public Programme(int IdProgramme,String Intitule, String Cours, String Debut, String Fin) {
               this.Intitule=Intitule;

        this.IdProgramme = IdProgramme;
        this.Cours = Cours;
        this.Debut = Debut;
        this.Fin = Fin;
    }

    public String getIntitule() {
        return Intitule;
    }

    public void setIntitule(String Intitule) {
        this.Intitule = Intitule;
    }

 

    public String getCours() {
        return Cours;
    }

    public String getDebut() {
        return Debut;
    }

    public String getFin() {
        return Fin;
    }

    public int getIdProgramme() {
        return IdProgramme;
    }

    public void setCours(String Cours) {
        this.Cours = Cours;
    }

    public void setDebut(String Debut) {
        this.Debut = Debut;
    }

    public void setFin(String Fin) {
        this.Fin = Fin;
    }

    public void setIdProgramme(int IdProgramme) {
        this.IdProgramme = IdProgramme;
    }

    @Override
    public String toString() {
        return "Programme{" + "IdProgramme=" + IdProgramme + ", Cours=" + Cours + ", Debut=" + Debut + ", Fin=" + Fin + '}';
    }
     

          
}
