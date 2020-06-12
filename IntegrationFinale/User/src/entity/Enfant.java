/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import Service.ServiceEnfant;
import java.sql.Date;
import java.sql.SQLException;


/**
 *
 * @author House
 */
public class Enfant {
    private int id_enfant;
    private String nom;
    private String prenom;
    private Date datenaissance;
    private String remarque;
    private String  image;
    private String document;
    private   String status="en attente";
    private User id_parent;
    private String cantine;

    public Enfant(int id_enfant, String nom, String prenom, Date datenaissance, String remarque, String image, String document, User id_parent, String cantine) {
        this.id_enfant = id_enfant;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.remarque = remarque;
        this.image = image;
        this.document = document;
        this.id_parent = id_parent;
        this.cantine = cantine;
       
    }
    public Enfant( String nom, String prenom, Date datenaissance, String remarque, String image, String document, User id_parent, String cantine) {
       // this.id_enfant = id_enfant;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.remarque = remarque;
        this.image = image;
        this.document = document;
        this.id_parent = id_parent;
        this.cantine = cantine;
       
    }
             public Enfant(int id_enfant,String nom, String prenom,Date datenaissance, String remarque,String image, User id_parent, String cantine, String document,String status) {
                 this.id_enfant=id_enfant;
                 this.nom=nom;
                 this.prenom=prenom;
                 this.datenaissance=datenaissance;
                 this.remarque=remarque;
                 this.image=image;
                 this.id_parent=id_parent;
                 this.cantine=cantine;
                 this.document=document;
                 this.status=status;
                 
             }
             
    public Enfant( String nom, String prenom, Date datenaissance, String remarque, String image, User id_parent , String cantine, String status) {
     
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.remarque = remarque;
        this.image = image;
        this.id_parent = id_parent;
        this.cantine = cantine;
          this.status= status;
    }
   
   public Enfant( int id_enfant) {
       this.id_enfant=id_enfant;
        
    }
    

    

    

    public int getId_enfant() {
        return id_enfant;
    }

    public void setId_enfant(int id_enfant) {
        this.id_enfant = id_enfant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getId_parent() {
        return id_parent;
    }

    public void setId_parent(User id_parent) {
        this.id_parent = id_parent;
    }

  

    public String getCantine() {
        return cantine;
    }

    public void setCantine(String cantine) {
        this.cantine = cantine;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        ServiceEnfant z=new ServiceEnfant();
        try { if(nom!=null)
            return "Enfant{" + "id_enfant=" + id_enfant + ", nom=" + z.AfficheLeNom(id_enfant) + ", prenom=" + prenom + ", datenaissance=" + datenaissance + ", remarque=" + remarque + ", image=" + image + ", document=" + document + ", status=" + status + ", id_parent=" + id_parent + ", cantine=" + cantine + '}';
           else
        {
            return  ""+z.AfficheLeNom(id_enfant)+"" ;
        }
        } 
        
        catch (SQLException ex) {
         
        }
        return null;
    }
   
 

    

    public String affiche() {
        return  ""+nom+"" ;
    }

    

    
    

    
    
    


  
    
}
