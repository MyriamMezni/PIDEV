/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;

/**
 *
 * @author asus
 */
public interface IserviceCantine<T> {
    
    void insert(T t);
    void update (T t);
    void delete(int id);
    List<T> displayAll(); 
}
