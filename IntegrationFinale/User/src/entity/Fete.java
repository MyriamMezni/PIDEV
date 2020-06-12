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
public class Fete extends Evenements{
    private String lieu;

    public Fete(String lieu, int id, String capacite, String nom, String heure_d, String description, String date_evt, String image, String type) {
        super(id, capacite, nom, heure_d, description, date_evt, image, type);
        this.lieu = lieu;
        
    }
     public Fete(String lieu, String capacite, String nom, String heure_d, String description, String date_evt, String image, String type) {
        super( capacite, nom, heure_d, description, date_evt, image, type);
        this.lieu = lieu;
        
    }

    public Fete() {
    }
    

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return super.toString()+"Fete{" + "lieu=" + lieu + '}';
    }
    
    
    
    
    
}
