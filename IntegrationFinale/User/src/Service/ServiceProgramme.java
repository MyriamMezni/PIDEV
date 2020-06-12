/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import entity.Activite;
import entity.Programme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Connexion;

/**
 *
 * @author ASUS
 */
public class ServiceProgramme implements IServiceProgramme<Programme>{
    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public ServiceProgramme() {
            cnx=Connexion.getInstance().getCnx();

    }

    @Override
    public void ajouterProgramme(Programme p) {
    String req="insert into programme (Intitule,Cours,Debut,Fin) values ('"+p.getIntitule()+"','"+p.getCours()+"','"+p.getDebut()+"','"+p.getFin()+"')";

        try {
            st=cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
                Logger.getLogger(ServiceProgramme.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }

    @Override
    public void modifierProgramme(Programme p) {

      int test=0;
    String req="Update programme set Intitule=?,Cours=?,Debut=?,Fin=? where IdProgramme=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(5,p.getIdProgramme());
             pst.setString(1,p.getIntitule());
             pst.setString(2,p.getCours());
            pst.setString(3,p.getDebut());
             pst.setString(4,p.getFin());

           test=pst.executeUpdate();
            if(test!=0)
             System.out.println("maj succes");
            else
            System.out.println("false");
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProgramme.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public boolean supprimerProgramme(int id) {

      int valeur=0;
        String req="delete from programme where IdProgramme=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1, id);
         valeur=  pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProgramme.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (valeur==0)
            return false;
        return true;
    }

    @Override
    public List<Programme> afficherProgramme() {
 String req="Select * from programme";
   List<Programme> list=new ArrayList<>();
        try {
            st=cnx.createStatement();
             rs=st.executeQuery(req);
             while(rs.next())
             {
                
                 
             list.add(new Programme(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5)));
             }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProgramme.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }
    
}
