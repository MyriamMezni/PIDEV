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
public class Excurtion extends Evenements{
   
    private String depart;
     private String destination;

    public Excurtion(String depart, String destination, int id, String capacite, String nom, String heure_d, String description, String date_evt, String image, String type) {
        super(id, capacite, nom, heure_d, description, date_evt, image, type);
       
        this.depart = depart;
        this.destination = destination;
        
    }
public Excurtion(String depart, String destination, String capacite, String nom, String heure_d, String description, String date_evt, String image, String type) {
        super(capacite, nom, heure_d, description, date_evt, image, type);
       
        this.depart = depart;
        this.destination = destination;
        
    }
    public Excurtion(String depart, String destination) {
        this.depart = depart;
        this.destination = destination;
    }
    

  

 
    

    public Excurtion() {
    }



    
    public String getDepart() {
        return depart;
    }

    public String getDestination() {
        return destination;
    }

   
    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

  
    @Override
    public String toString() {
        return super.toString()+"Excurtion{" + "depart=" + depart + ", destination=" + destination +  '}';
    }

    
     
    
    
}
