/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ben younes
 */
public class Connexion {
    private String url="jdbc:mysql://127.0.0.1/pidev";//private String url="jdbc:mysql://127.0.0.1:3306/esprit" avec 3306
    private String username="root";
    private String password="";
    private Connection cnx;
    private  static Connexion instance;
    private Connexion()
    {
        try {
            cnx=DriverManager.getConnection(url, username, password);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Connexion getInstance()
    {
        if (instance==null)
            instance=new Connexion();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
