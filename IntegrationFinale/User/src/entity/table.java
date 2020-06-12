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
public class table {
    
    
    
    private int id;
    private int capacite;

    public table(int id, int capacite) {
        this.id = id;
        this.capacite = capacite;
    }
    public table(int capacite) {
        this.capacite = capacite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "table{" + "id=" + id + ", capacite=" + capacite + '}';
    }
    
    
}
