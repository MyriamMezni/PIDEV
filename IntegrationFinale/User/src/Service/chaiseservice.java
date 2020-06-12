/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Chaise;
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
 * @author asus
 */
public class chaiseservice implements IserviceCantine<Chaise>{
    
    private Connection cnx;
 
    private PreparedStatement pst;
    private ResultSet rs;
    
    
    public chaiseservice(){
        cnx=Connexion.getInstance().getCnx();
    }

    @Override
    public void insert(Chaise ch) {
        String req="insert into chaise (etat_plat,num_table) values (?,?)";
        System.out.println(ch);
        try {
            System.out.println(ch);
            pst=cnx.prepareStatement(req);
            
           // pst.setInt(1,ch.getId_enfant());
            
            pst.setString(1,ch.getEtat_plat());
            pst.setInt(2,ch.getNum_table());
            pst.executeUpdate();
           // System.out.println("okkk");
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void insert_avecidenfant(Chaise ch) {
        String req="insert into chaise (id_enfant,etat_plat,num_table) values (?,?,?)";
        System.out.println(ch);
        try {
            System.out.println(ch);
            pst=cnx.prepareStatement(req);
            
           pst.setInt(1,ch.getId_enfant());
            
            pst.setString(2,ch.getEtat_plat());
            pst.setInt(3,ch.getNum_table());
            pst.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Chaise ch) {
         String req="update chaise set etat_plat=?,num_table=? where id_chaise=?";
        try {
            
            pst=cnx.prepareStatement(req);
            pst.setInt(3,ch.getId_chaise());
          //  pst.setInt(1,ch.getId_enfant());
            pst.setString(1,ch.getEtat_plat());
            pst.setInt(2,ch.getNum_table());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
         String req="delete from chaise where id_chaise=?";
                  try {
            
            pst=cnx.prepareStatement(req);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    
    public void deleteParTable(int id) {
         String req="delete from chaise where num_table=?";
                  try {
            
            pst=cnx.prepareStatement(req);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }

    @Override
    public List<Chaise> displayAll() {
      String req="select * from chaise";
            List<Chaise> list=new ArrayList<>();
            try {
            pst=cnx.prepareStatement(req);
            rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Chaise(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
            return list;
    }
    
         public List<Chaise> chercher_parid(int id) {
                      String req="select * from chaise where id_enfant=?";
            List<Chaise> list=new ArrayList<>();
            try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Chaise(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
            return list;
    }
    public Chaise getChaise(int id)
    {
        String req="select * from chaise where id_chaise=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1,id);
            rs=pst.executeQuery();
            if(rs.first())
            {
                //System.out.println("num:"+rs.getInt(4));
                return new Chaise(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4));
            }
            else
            {
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<Integer> getIds() {
              String req="select id_chaise from chaise";
            List<Integer> list=new ArrayList<>();
            try {
            pst=cnx.prepareStatement(req);
            rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Integer(rs.getInt(1)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
            return list;
    }
    public boolean chaiseVide()
    {
        String rep="select * from chaise where id_enfant is null";
        try {
            pst=cnx.prepareStatement(rep);
            rs=pst.executeQuery();
            return rs.first();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    public int numChaiseVide()
    {
        String rep="select id_chaise from chaise where id_enfant is null";
        try {
            pst=cnx.prepareStatement(rep);
            rs=pst.executeQuery();
            if(rs.first())
            {
                return rs.getInt("id_chaise");
            }
            else
            {
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    public void majEnfant(int id_chaise,int id_enfant)
    {
        String rep="update chaise set id_enfant=? where id_chaise=?";
        try {
            pst=cnx.prepareStatement(rep);
            pst.setInt(1, id_enfant);
            pst.setInt(2, id_chaise);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void majEnfantNull(int id_chaise)
    {
        String rep="update chaise set id_enfant=null where id_chaise=?";
        try {
            pst=cnx.prepareStatement(rep);
            pst.setInt(1, id_chaise);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public int getChaiseParEnfant(int id_enfant)
    {
        String rep="select id_chaise from chaise where id_enfant=?";
        try {
            pst=cnx.prepareStatement(rep);
            pst.setInt(1, id_enfant);
            rs=pst.executeQuery();
            if(rs.first())
            {
                return rs.getInt("id_chaise");
            }
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
    }
}
