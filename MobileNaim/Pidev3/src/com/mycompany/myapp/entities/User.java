/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Calendar;
import java.util.Date;



/**
 *
 * @author ben younes
 */
public class User {
    private int idUser;
    private String username;
    private String username_canonical;
    private String email;
    private String email_canonical;
    private int enabled;
    private String salt;
    private String password;
    private Date last_login;
    private String confirmation_token;
    private Date password_requested_at;
    private String roles;
    private String nom;
    private String prenom;
    private String image;
    private String mdp;
    private int NumTel;
    private Date DateNaissance;
    private String Region;
    private String Ville;
    private String Rue;
    private int CodePostal;
    private int Tarif;
    private int NbEnfant;
    private String role;

    public User(int idUser, String username, String username_canonical, String email, String email_canonical, String password, String nom, String prenom, String image, String mdp, int NumTel, Date DateNaissance, String Region, String Ville, String Rue, int CodePostal, String role) {
        this.idUser = idUser;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
        this.mdp = mdp;
        this.NumTel = NumTel;
        this.DateNaissance = DateNaissance;
        this.Region = Region;
        this.Ville = Ville;
        this.Rue = Rue;
        this.CodePostal = CodePostal;
        this.role = role;
        this.roles="a:1:{i:0;s:11:\"ROLE_PARENT\";}";
        this.enabled=1;
    }

    public User(int idUser, String username, String username_canonical, String email, String email_canonical, int enabled, String salt, String password, Date last_login, String confirmation_token, Date password_requested_at, String roles, String nom, String prenom, String image, String mdp, int NumTel, Date DateNaissance, String Region, String Ville, String Rue, int CodePostal, int Tarif, int NbEnfant, String role) {
        this.idUser = idUser;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.last_login = last_login;
        this.confirmation_token = confirmation_token;
        this.password_requested_at = password_requested_at;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
        this.mdp = mdp;
        this.NumTel = NumTel;
        this.DateNaissance = DateNaissance;
        this.Region = Region;
        this.Ville = Ville;
        this.Rue = Rue;
        this.CodePostal = CodePostal;
        this.Tarif = Tarif;
        this.NbEnfant = NbEnfant;
        this.role = role;
    }

    public User(int idUser, String username, String email, String password, String nom, String prenom, String image, int NumTel, Date DateNaissance, String Region, String Ville, String Rue, int CodePostal) {
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
        this.NumTel = NumTel;
        this.DateNaissance = DateNaissance;
        this.Region = Region;
        this.Ville = Ville;
        this.Rue = Rue;
        this.CodePostal = CodePostal;
    }

    public User(int idUser) {
        this.idUser=idUser;  //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
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


    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String Ville) {
        this.Ville = Ville;
    }

    public String getRue() {
        return Rue;
    }

    public void setRue(String Rue) {
        this.Rue = Rue;
    }

    public int getCodePostal() {
        return CodePostal;
    }

    public void setCodePostal(int CodePostal) {
        this.CodePostal = CodePostal;
    }


    public int getTarif() {
        return Tarif;
    }

    public void setTarif(int Tarif) {
        this.Tarif = Tarif;
    }

    public int getNbEnfant() {
        return NbEnfant;
    }

    public void setNbEnfant(int NbEnfant) {
        this.NbEnfant = NbEnfant;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "idUser=" + idUser + 
               "\n username=" + username +
               "\n username_canonical=" + username_canonical + 
               "\n email=" + email +
               "\n email_canonical=" + email_canonical + 
               "\n enabled=" + enabled +
               "\n salt=" + salt +
               "\n password=" + password +
               "\n last_login=" + last_login +
               "\n confirmation_token=" + confirmation_token +
               "\n password_requested_at=" + password_requested_at + 
               "\n roles=" + roles +
               "\n nom=" + nom + 
               "\n prenom=" + prenom + 
               "\n image=" + image + 
               "\n mdp=" + mdp + 
               "\n NumTel=" + NumTel +
               "\n DateNaissance=" + DateNaissance + 
               "\n Region=" + Region +
               "\n Ville=" + Ville + 
               "\n Rue=" + Rue + 
               "\n CodePostal=" + CodePostal + 
               "\n Tarif=" + Tarif + 
               "\n NbEnfant=" + NbEnfant + 
               "\n role=" + role ;
    }
    public String generateurl()
    {
        Calendar c=Calendar.getInstance();
        c.setTime(DateNaissance);
        return idUser+"/"+username+"/"+email+"/"+password+"/"+nom+"/"+prenom+"/"+image+"/"+NumTel+"/"+c.get(Calendar.DATE)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR)+"/"+c.get(Calendar.HOUR_OF_DAY)+"/"+c.get(Calendar.MINUTE)+"/"+Region+"/"+Ville+"/"+Rue+"/"+CodePostal;
        
    }
    
    
    
    
}
