/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import Service.EmployeService;

/**
 *
 * @author ben younes
 */
public class User {
    protected int Id_User;
    protected String username;
    protected String username_canonical;
    protected String nom;
    protected String prenom;
    protected String Email;
    protected String email_canonical;
    protected String pasword;
    protected String Roles;
    protected String MDP;
    protected String Image;
    protected int NumTel;
    protected int bloque;
    public User() {
    }
    public User(int Id_User) {
        this.Id_User = Id_User;
    }

    public User(int Id_User, String nom, String prenom, String Email, String MDP, String Image, int NumTel) {
        this.Id_User = Id_User;
        this.nom = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.MDP = MDP;
        this.Image = Image;
        this.NumTel = NumTel;
    }

    public User(String nom, String prenom, String Email, String MDP, String Image, int NumTel) {
        this.nom = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.MDP = MDP;
        this.Image = Image;
        this.NumTel = NumTel;
    }

    public User(int Id_User, String nom, String prenom, String Email, String MDP, int NumTel) {
        this.Id_User = Id_User;
        this.nom = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.MDP = MDP;
        this.NumTel = NumTel;
    }

    public User(String nom, String prenom, String Email, String MDP, int NumTel) {
        this.nom = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.MDP = MDP;
        this.NumTel = NumTel;
    }

    public int getBloque() {
        return bloque;
    }

    public void setBloque(int bloque) {
        this.bloque = bloque;
    }

    public User(int Id_User, String nom, String prenom, String Email, String MDP, String Image, int NumTel, int bloque) {
        this.Id_User = Id_User;
        this.nom = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.MDP = MDP;
        this.Image = Image;
        this.NumTel = NumTel;
        this.bloque = bloque;
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

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getNumTel() {
        return NumTel;
    }

    public void setNumTel(int NumTel) {
        this.NumTel = NumTel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getRoles() {
        return Roles;
    }

    public void setRoles(String Roles) {
        this.Roles = Roles;
    }

    
    
    @Override
    public String toString() {
    EmployeService z=new EmployeService();
        if(nom!=null)
            return "User:" + "Id_User=" + Id_User + ", nom=" + nom + ", prenom=" + prenom + ", Email=" + Email + ", MDP=" + MDP + ", Image=" + Image + ", NumTel=" + NumTel + '\n';
        else
        {
            return  ""+z.rechercheParId(Id_User)+"" ;
        }    }
    
}
