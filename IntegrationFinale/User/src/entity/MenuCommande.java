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
public class MenuCommande {
    
    
    private Menu m;
    private int id_enfant;
    private String jour_de_la_semaine;

    public MenuCommande(Menu m, int id_enfant, String jour_de_la_semaine) {
        this.m = m;
        this.id_enfant = id_enfant;
        this.jour_de_la_semaine = jour_de_la_semaine;
    }

    public Menu getM() {
        return m;
    }

    public void setM(Menu m) {
        this.m =m;
    }

    public int getId_enfant() {
        return id_enfant;
    }

    public void setId_enfant(int id_enfant) {
        this.id_enfant = id_enfant;
    }

    public String getJour_de_la_semaine() {
        return jour_de_la_semaine;
    }

    public void setJour_de_la_semaine(String jour_de_la_semaine) {
        this.jour_de_la_semaine = jour_de_la_semaine;
    }

    @Override
    public String toString() {
        return "MenuCommande{" + "id_menu=" + m.getId() + ", id_enfant=" + id_enfant + ", jour_de_la_semaine=" + jour_de_la_semaine + '}';
    }
    
    
    
}
