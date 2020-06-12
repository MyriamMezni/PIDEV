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
public class Admin  extends User{

    public Admin(int Id_User, String nom, String prenom, String Email, String MDP, String Image, int NumTel) {
        super(Id_User, nom, prenom, Email, MDP, Image, NumTel);
    }

    public Admin(int Id_User, String nom, String prenom, String Email, String MDP, int NumTel) {
        super(Id_User, nom, prenom, Email, MDP, NumTel);
    }

    public Admin(String nom, String prenom, String Email, String MDP, int NumTel) {
        super(nom, prenom, Email, MDP, NumTel);
    }

    public Admin(int Id_User, String nom, String prenom, String Email, String MDP, String Image, int NumTel, int bloque) {
        super(Id_User, nom, prenom, Email, MDP, Image, NumTel, bloque);
    }
    
    
}
