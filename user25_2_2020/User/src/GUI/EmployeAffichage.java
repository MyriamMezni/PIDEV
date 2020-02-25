/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Employe;
import java.io.File;
import java.sql.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author ben younes
 */
public class EmployeAffichage {
    int Id_User;
    private String nom;
    private String prenom;
    private String Email;
    private String MDP;
    private ImageView im;
    int NumTel;
    private Date DateNaissance;
    private float Salaire;
    private int NbHeures;
    private String type;
    private String region;
    private String ville;
    private String Rue;
    private String codePostal;

    public EmployeAffichage() {
       im=new ImageView();
    }

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int Id_User) {
        this.Id_User = Id_User;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMDP() {
        return MDP;
    }

    public void setMDP(String MDP) {
        this.MDP = MDP;
    }

    public ImageView getIm() {
        return im;
    }

    public void setIm(ImageView im) {
        this.im = im;
    }

    public int getNumTel() {
        return NumTel;
    }

    public void setNumTel(int NumTel) {
        this.NumTel = NumTel;
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

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    public void transformer(Employe e)
    {
        Id_User=e.getId_User();
        nom=e.getNom();
        prenom=e.getPrenom();
        File f= new File(e.getImage());
        Image i=new Image(f.toURI().toString());
        im.setImage(i);
        im.setFitHeight(100);
        im.setFitWidth(100);
        DateNaissance=e.getDateNaissance();
        Salaire=e.getSalaire();
        NbHeures=e.getNbHeures();
    }
}
