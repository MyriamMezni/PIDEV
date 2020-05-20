/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Parent;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author ben younes
 */
public class AffichageParent {
    private int Id_User;
    private String nom;
    private String prenom;
    private String Email;
    private String MDP;
    private ImageView im;
    private int NumTel;
    private String region;
    private String ville;
    private String Rue;
    private String CodePostal;
    private float Tarif;
    private int NbEnfant;

    public AffichageParent() {
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

    public ImageView getImage() {
        return im;
    }

    public void setImage(ImageView Image) {
        this.im = Image;
    }

    public int getNumTel() {
        return NumTel;
    }

    public void setNumTel(int NumTel) {
        this.NumTel = NumTel;
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
        return CodePostal;
    }

    public void setCodePostal(String CodePostal) {
        this.CodePostal = CodePostal;
    }

    public float getTarif() {
        return Tarif;
    }

    public void setTarif(float Tarif) {
        this.Tarif = Tarif;
    }

    public int getNbEnfant() {
        return NbEnfant;
    }

    public void setNbEnfant(int NbEnfant) {
        this.NbEnfant = NbEnfant;
    }
    public void transformer(Parent p)
    {
        Id_User=p.getId_User();
        nom=p.getNom();
        MDP=p.getMDP();
        prenom=p.getPrenom();
        Email=p.getEmail();
        ville=p.getVille();
        region=p.getRegion();
        Rue=p.getRue();
        Tarif=p.getTarif();
        NumTel=p.getNumTel();
        NbEnfant=p.getNbEnfant();
        File f=new File(p.getImage());
        Image i=new Image(f.toURI().toString());
        //System.out.println(f.toURI().toString());
        im.setImage(i);
        im.setFitHeight(100);
        im.setFitWidth(100);
        
        
    }
    
}
