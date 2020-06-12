/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IServiceActivite<T>{
    
     void ajouterActivite(T t);
 void modifierActivite(T t);
 boolean supprimerActivite(int id);
 List<T> afficherActivite();
 
       void recherecher(int id);
               List<T>  Trier();

}
