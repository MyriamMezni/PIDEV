/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Activite;
import entity.RatingActivite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Connexion;

/**
 *
 * @author ASUS
 */
public class ServiceRating {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public ServiceRating() {
                    cnx=Connexion.getInstance().getCnx();

    }
     public void ajouterActivite(RatingActivite t) {

        String req="insert into ratingactivite (Intitule,Rate) values ('"+t.getIntitule()+"','"+t.getRate()+"')";
        try {
            st=cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
                Logger.getLogger(ServiceActivite.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
}
