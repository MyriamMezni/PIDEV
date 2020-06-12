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
public class RatingActivite {
    private String Intitule;
    private int    Rate;

    public RatingActivite() {
    }

    public RatingActivite(String Intitule, int Rate) {
        this.Intitule = Intitule;
        this.Rate = Rate;
    }

    public void setIntitule(String Intitule) {
        this.Intitule = Intitule;
    }

    public void setRate(int Rate) {
        this.Rate = Rate;
    }

    public String getIntitule() {
        return Intitule;
    }

    public int getRate() {
        return Rate;
    }
    
    
}
