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
public interface IServiceProgramme<T> {
    void ajouterProgramme(T t);
 void modifierProgramme(T t);
 boolean supprimerProgramme(int id);
 List<T> afficherProgramme();
 
}
