/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import entity.Activite;
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
public class ServiceActivite  implements IServiceActivite<Activite>{
    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public ServiceActivite() {
    cnx=Connexion.getInstance().getCnx();

    }
    

    @Override
  public void recherecher(int id){
        int i=0;
          String req="select * from activite WHERE IdActivite="+id+"";
        try {
          st=cnx.createStatement();
          rs=st.executeQuery(req);

          while(rs.next())
            {int Id = rs.getInt(1); 
                String Intitule=rs.getString(2);
                 String Niveau=rs.getString(3);
                 String Responsable=rs.getString(4);
                 String Type=rs.getString(5);
                 String HeureDebut=rs.getTime(6).toString();
                 String HeureFin=rs.getTime(7).toString();

               
                System.out.println(Intitule +" "+ Niveau+ " "+Id+" "+Responsable+" "+Type+" "+HeureDebut+" "+HeureFin); 
            }
           if ( rs.first()==false)
                { System.out.println("famech  "+id+"haka ");}
          
          
          
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActivite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
    }

    @Override
    public void ajouterActivite(Activite t) {

    String req="insert into activite (Intitule,Niveau,Responsable,Type,HeureDebut,HeureFin) values ('"+t.getIntitule()+"','"+t.getNiveau()+"','"+t.getResponsable()+"','"+t.getType()+"','"+t.getHeureDebut()+"','"+t.getHeureFin()+"')";
        try {
            st=cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
                Logger.getLogger(ServiceActivite.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }

    @Override
    public void modifierActivite(Activite t) {
    
       int test=0;
    String req="Update activite set Intitule=?,Niveau=?,Responsable=?,Type=?,HeureDebut=?,HeureFin=? where IdActivite=?";
        try {
            pst=cnx.prepareStatement(req);
                        pst.setInt(7,t.getIdActivite());

             pst.setString(1,t.getIntitule());
            pst.setString(2,t.getNiveau());
             pst.setString(3,t.getResponsable());
             pst.setString(4,t.getType());
           pst.setString(5,t.getHeureDebut());
             pst.setString(6,t.getHeureFin());

           test=pst.executeUpdate();
            if(test!=0)
             System.out.println("maj succes");
            else
            System.out.println("false");
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActivite.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public boolean supprimerActivite(int id) {

     int valeur=0;
        String req="delete from activite where IdActivite=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1, id);
         valeur=  pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActivite.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (valeur==0)
            return false;
        return true;
    
    }

    @Override
    public List<Activite> afficherActivite() {

     String req="Select * from activite";
   List<Activite> list=new ArrayList<>();
        try {
            st=cnx.createStatement();
             rs=st.executeQuery(req);
             while(rs.next())
             {
                
                 
             list.add(new Activite(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
             }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActivite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }

    @Override
    public List<Activite> Trier() {
     String req="Select * from activite order by Intitule desc ";
   List<Activite> list=new ArrayList<>();
        try {
            st=cnx.createStatement();
             rs=st.executeQuery(req);
             while(rs.next())
             {
                
                 
             list.add(new Activite(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
             }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActivite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }
    
}
