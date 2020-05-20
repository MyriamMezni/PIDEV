/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ben younes
 */
public class Parent extends User{
    
    private String region;
    private String ville;
    private String Rue;
    private String CodePostal;
    private float Tarif;
    private int NbEnfant;

    public Parent(String region, String ville, String Rue, String CodePostal, float Tarif, int NbEnfant, int Id_User, String nom, String prenom, String Email, String MDP, String Image, int NumTel) {
        super(Id_User, nom, prenom, Email, MDP, Image, NumTel);
        this.region = region;
        this.ville = ville;
        this.Rue = Rue;
        this.CodePostal = CodePostal;
        this.Tarif = Tarif;
        this.NbEnfant = NbEnfant;
    }

    public Parent(String region, String ville, String Rue, String CodePostal, float Tarif, int NbEnfant, String nom, String prenom, String Email, String MDP, String Image, int NumTel) {
        super(nom, prenom, Email, MDP, Image, NumTel);
        this.region = region;
        this.ville = ville;
        this.Rue = Rue;
        this.CodePostal = CodePostal;
        this.Tarif = Tarif;
        this.NbEnfant = NbEnfant;
    }

    public Parent(String region, String ville, String Rue, String CodePostal, float Tarif, int NbEnfant, int Id_User, String nom, String prenom, String Email, String MDP, int NumTel) {
        super(Id_User, nom, prenom, Email, MDP, NumTel);
        this.region = region;
        this.ville = ville;
        this.Rue = Rue;
        this.CodePostal = CodePostal;
        this.Tarif = Tarif;
        this.NbEnfant = NbEnfant;
    }

    public Parent(String region, String ville, String Rue, String CodePostal, float Tarif, int NbEnfant, String nom, String prenom, String Email, String MDP, int NumTel) {
        super(nom, prenom, Email, MDP, NumTel);
        this.region = region;
        this.ville = ville;
        this.Rue = Rue;
        this.CodePostal = CodePostal;
        this.Tarif = Tarif;
        this.NbEnfant = NbEnfant;
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

    

    @Override
    public String toString() {
        return "Parent:" + "Id_User=" + Id_User + ", nom=" + nom + ", prenom=" + prenom + ", Email=" + Email + ", MDP=" + MDP + ", Image=" + Image + ", NumTel=" + NumTel + ", region=" + region + ", ville=" + ville + ", Rue=" + Rue + ", CodePostal=" + CodePostal + ", Tarif=" + Tarif + ", NbEnfant=" + NbEnfant + '\n';
    }
    
    
    
    
}
