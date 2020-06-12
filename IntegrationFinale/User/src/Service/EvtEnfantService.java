/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import util.Connexion;
import entity.EvtEnfant;
import entity.Excurtion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class EvtEnfantService implements IServiceEvt<EvtEnfant> {
       private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public EvtEnfantService() {
                    cnx=Connexion.getInstance().getCnx();

    }


    @Override
    public void insert(EvtEnfant t) {
        String req="insert into inscrevt (id,id_enfant) values('"+t.getId()+"','"+t.getIdenfant()+"')";
         System.out.println(t);
      try {
            st=cnx.createStatement();
            
            st.executeUpdate(req);
            
        } catch (SQLException ex) {
            Logger.getLogger(EvtEnfantService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(EvtEnfant t) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public List<EvtEnfant> displayAll() {
    return null;   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
