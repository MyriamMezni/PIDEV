/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;

/**
 *
 * @author ben younes
 */
public interface Iservice<T> {
    public void insert(T t);
    public void delete(int id);
    public void update(T t);
    public List<T> displayAll();
    public List<T> rechercheNom(String Nom);
    
    
}
