/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author ben younes
 */
public class Employe extends User{
    
    private Date DateNaissance;
    private float Salaire;
    private int NbHeures;
    private String type;
    private String region;
    private String ville;
    private String Rue;
    private String codePostal;

    public Employe(Date DateNaissance, float Salaire, int NbHeures, String type, String region, String ville, String Rue,String codepostal, int Id_User, String nom, String prenom, String Email, String MDP, String Image, int NumTel) {
        super(Id_User, nom, prenom, Email, MDP, Image, NumTel);
        this.DateNaissance = DateNaissance;
        this.Salaire = Salaire;
        this.NbHeures = NbHeures;
        this.type = type;
        this.region = region;
        this.ville = ville;
        this.Rue = Rue;
        this.codePostal=codepostal;
    }

    public Employe(Date DateNaissance, float Salaire, int NbHeures, String type, String region, String ville, String Rue,String codepostal, String nom, String prenom, String Email, String MDP, String Image, int NumTel) {
        super(nom, prenom, Email, MDP, Image, NumTel);
        this.DateNaissance = DateNaissance;
        this.Salaire = Salaire;
        this.NbHeures = NbHeures;
        this.type = type;
        this.region = region;
        this.ville = ville;
        this.Rue = Rue;
        this.codePostal=codepostal;
    }

    public Employe(Date DateNaissance, float Salaire, int NbHeures, String type, String region, String ville, String Rue,String codepostal, int Id_User, String nom, String prenom, String Email, String MDP, int NumTel) {
        super(Id_User, nom, prenom, Email, MDP, NumTel);
        this.DateNaissance = DateNaissance;
        this.Salaire = Salaire;
        this.NbHeures = NbHeures;
        this.type = type;
        this.region = region;
        this.ville = ville;
        this.Rue = Rue;
        this.codePostal=codepostal;
    }

    public Employe(Date DateNaissance, float Salaire, int NbHeures, String type, String region, String ville, String Rue,String codepostal, String nom, String prenom, String Email, String MDP, int NumTel) {
        super(nom, prenom, Email, MDP, NumTel);
        this.DateNaissance = DateNaissance;
        this.Salaire = Salaire;
        this.NbHeures = NbHeures;
        this.type = type;
        this.region = region;
        this.ville = ville;
        this.Rue = Rue;
        this.codePostal=codepostal;
    }

    public Employe(Date DateNaissance, float Salaire, int NbHeures, String type, String region, String ville, String Rue, String codePostal, int Id_User, String nom, String prenom, String Email, String MDP, String Image, int NumTel, int bloque) {
        super(Id_User, nom, prenom, Email, MDP, Image, NumTel, bloque);
        this.DateNaissance = DateNaissance;
        this.Salaire = Salaire;
        this.NbHeures = NbHeures;
        this.type = type;
        this.region = region;
        this.ville = ville;
        this.Rue = Rue;
        this.codePostal = codePostal;
    }
    
    public Date getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(Date DateNaissance) {
        this.DateNaissance = DateNaissance;
    }

    public float getSalaire() {
        return Salaire;
    }

    public void setSalaire(float Salaire) {
        this.Salaire = Salaire;
    }

    public int getNbHeures() {
        return NbHeures;
    }

    public void setNbHeures(int NbHeures) {
        this.NbHeures = NbHeures;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return Rue;
    }

    public void setRue(String Rue) {
        this.Rue = Rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codepostal) {
        this.codePostal = codepostal;
    }
    
    @Override
    public String toString() {
        return "Employe:" + "Id_User=" + Id_User + ", nom=" + nom + ", prenom=" + prenom + ", Email=" + Email + ", MDP=" + MDP + ", Image=" + Image + ", NumTel=" + NumTel + ", DateNaissance=" + DateNaissance + ", Salaire=" + Salaire + ", NbHeures=" + NbHeures + ", type=" + type + ", region=" + region + ", ville=" + ville + ", Rue=" + Rue +", CodePostal=" + codePostal +", UserName="+username+ '\n';
    }
    
    
    
    
}
