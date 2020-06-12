/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ASUS
 */
public class EvtEnfant {
    private int id;
    private int idenfant;

    public EvtEnfant(int id, int idenfant) {
        this.id = id;
        this.idenfant = idenfant;
    }

    public int getId() {
        return id;
    }

   
    public void setId(int id) {
        this.id = id;
    }

    public void setIdenfant(int idenfant) {
        this.idenfant = idenfant;
    }

    public int getIdenfant() {
        return idenfant;
    }

    @Override
    public String toString() {
        return "EvtEnfant{" + "id=" + id + ", idenfant=" + idenfant + '}';
    }

  

  
    
    
    
    
}
