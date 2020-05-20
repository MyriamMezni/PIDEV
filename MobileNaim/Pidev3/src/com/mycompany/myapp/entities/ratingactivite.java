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
public class ratingactivite {
    private int IdRate;
    private String Intitule;
    private int Rate;

    public ratingactivite() {
    }

    public ratingactivite(int IdRate, String Intitule, int Rate) {
        this.IdRate = IdRate;
        this.Intitule = Intitule;
        this.Rate = Rate;
    }
    
     public ratingactivite( String Intitule, int Rate) {
      
        this.Intitule = Intitule;
        this.Rate = Rate;
    }

    public int getIdRate() {
        return IdRate;
    }

    public void setIdRate(int IdRate) {
        this.IdRate = IdRate;
    }

    public String getIntitule() {
        return Intitule;
    }

    public void setIntitule(String Intitule) {
        this.Intitule = Intitule;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int Rate) {
        this.Rate = Rate;
    }

    @Override
    public String toString() {
        return "ratingactivite{" + "IdRate=" + IdRate + ", Intitule=" + Intitule + ", Rate=" + Rate + '}';
    }
     
    
}
