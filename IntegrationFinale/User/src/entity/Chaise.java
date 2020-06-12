/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author asus
 */
public class Chaise {
    
    private int id_chaise;
    private int id_enfant;
    private String etat_plat;
    private int num_table;

    public Chaise(int id_chaise, int id_enfant, String etat_plat, int num_table) {
        this.id_chaise = id_chaise;
        this.id_enfant = id_enfant;
        this.etat_plat = etat_plat;
        this.num_table = num_table;
    }
    public Chaise(int id_chaise, String etat_plat, int num_table) {
        this.id_chaise = id_chaise;
        this.etat_plat = etat_plat;
        this.num_table = num_table;
    }


    public Chaise(String etat_plat, int num_table) {
        this.etat_plat = etat_plat;
        this.num_table = num_table;
    }
    
   
    public int getId_chaise() {
        return id_chaise;
    }

    public void setId_chaise(int id_chaise) {
        this.id_chaise = id_chaise;
    }

    public int getId_enfant() {
        return id_enfant;
    }

    public void setId_enfant(int id_enfant) {
        this.id_enfant = id_enfant;
    }

    public String getEtat_plat() {
        return etat_plat;
    }

    public void setEtat_plat(String etat_plat) {
        this.etat_plat = etat_plat;
    }

    public int getNum_table() {
        return num_table;
    }

    public void setNum_table(int num_table) {
        this.num_table = num_table;
    }

    @Override
    public String toString() {
        return "Chaise{" + "id_chaise=" + id_chaise + ", id_enfant=" + id_enfant + ", etat_plat=" + etat_plat + ", num_table=" + num_table + '}';
    }
    
    
}
