/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Menu;
import entity.table;
//import static java.awt.SystemColor.menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Connexion;

/**
 *
 * @author asus
 */
public class manuservice implements IserviceCantine<Menu>{
    
     private Connection cnx;
 
    private PreparedStatement pst;
    private ResultSet rs;
    
    
    public manuservice(){
        cnx=Connexion.getInstance().getCnx();
    }

    @Override
    public void insert(Menu m) {
                String req="insert into menu (nom,image,description,jour_de_la_semaine) values (?,?,?,?)";
        
        try {
           
            pst=cnx.prepareStatement(req);
            
           // pst.setInt(1,ch.getId_enfant());
            
            pst.setString(1,m.getNom());
            pst.setString(2,m.getImage());
            pst.setString(3,m.getDescription());
            pst.setString(4,m.getJour_de_la_semaine());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Menu m) {
          
                 String req="update menu set nom=?,image=?,description=?,jour_de_la_semaine=? where id=?";
        try {
            
            pst=cnx.prepareStatement(req);
            pst.setInt(5,m.getId());
          //  pst.setInt(1,ch.getId_enfant());
            pst.setString(1,m.getNom());
            pst.setString(2,m.getImage());
            pst.setString(3,m.getDescription());
            pst.setString(4,m.getJour_de_la_semaine());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
                       String req="delete from menu where id=?";
                  try {
            
            pst=cnx.prepareStatement(req);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Menu> displayAll() {
                      String req="select * from menu";
            List<Menu> list=new ArrayList<>();
            try {
            pst=cnx.prepareStatement(req);
            rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Menu(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
            return list;
    }
    
    
     public List<Menu> chercher_parnom(String nom) {
            String req="select * from menu where nom like ?";
            List<Menu> list=new ArrayList<>();
            try {
            pst=cnx.prepareStatement(req);
            pst.setString(1,nom+"%");
            rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Menu(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
            return list;
    }
     
     
     public Menu rechercher_menuId(int id)
     {
 
             String req="select * from menu where id=?";
             try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1,id);
            rs=pst.executeQuery();
            if(rs.first())
            {
                return new Menu(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            }
            else {
                return null;
            }
          } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
             
     }
     
     public List<Integer> getIds() {
            String req="select id from menu";
            List<Integer> list=new ArrayList<>();
            try {
            pst=cnx.prepareStatement(req);
            rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Integer(rs.getInt("id")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
            return list;
    }
     public List<Menu> getParjour(String jour)
     {
         String req="select * from menu where jour_de_la_semaine like ?";
            List<Menu> list=new ArrayList<>();
            try {
            pst=cnx.prepareStatement(req);
            pst.setString(1,"%"+jour+"%");
            rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Menu(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
            return list;
     }
     public String getNom(int id)
     {
         String req="select nom from menu where id=?";
         try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1,id);
            rs=pst.executeQuery();
            if(rs.first())
            {
                return rs.getString(1);
            }
            else
            {
                return "";
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
     }
     
    
    
}
