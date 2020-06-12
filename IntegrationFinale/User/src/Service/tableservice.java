/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Chaise;
import entity.table;
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
public class tableservice implements IserviceCantine<table> {
    
     private Connection cnx;
 
    private PreparedStatement pst;
    private ResultSet rs;
    
    
    public tableservice(){
        cnx=Connexion.getInstance().getCnx();
    }

    @Override
    public void insert(table t) {
       String req="insert into table_cantine (capacite) values (?)";
        //System.out.println();
        try {
            //System.out.println(ch);
            pst=cnx.prepareStatement(req);
            
           // pst.setInt(1,ch.getId_enfant());
            
            pst.setInt(1,t.getCapacite());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(table t) {
                 String req="update table_cantine set capacite=? where id_table=?";
        try {
            
            pst=cnx.prepareStatement(req);
            pst.setInt(2,t.getId());
          //  pst.setInt(1,ch.getId_enfant());
            pst.setInt(1,t.getCapacite());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
                 String req="delete from table_cantine where id_table=?";
                  try {
            
            pst=cnx.prepareStatement(req);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<table> displayAll() {
              String req="select * from table_cantine";
            List<table> list=new ArrayList<>();
            try {
            pst=cnx.prepareStatement(req);
            rs=pst.executeQuery();
            while(rs.next()){
                list.add(new table(rs.getInt(1),rs.getInt(2)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
            return list;
    }
    public List<Integer> getIds() {
              String req="select id_table from table_cantine";
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
    
    public List<Integer> getIdsDispo() {
              String req="select id_table from table_cantine where capacite>0";
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
    public int getCapacite(int id)
    {
        String req="select capacite from table_cantine where id_table=?";
        try {
            
            pst=cnx.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            if(rs.first())
            {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(tableservice.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 0;
    }
    public void decrimenter(int id)
    {
         String req="update table_cantine set capacite=? where id_table=?";
        try {
            
            pst=cnx.prepareStatement(req);
            pst.setInt(2,id);
          //  pst.setInt(1,ch.getId_enfant());
          int cap=getCapacite(id)-1;
            System.out.println(cap);
            pst.setInt(1,cap);
            pst.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
    public void imcrementer(int id)
    {
         String req="update table_cantine set capacite=? where id_table=?";
        try {
            
            pst=cnx.prepareStatement(req);
            pst.setInt(2,id);
          //  pst.setInt(1,ch.getId_enfant());
            pst.setInt(1,getCapacite(id)+1);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public table getTable(int id)
    {
        String req="select * from table_cantine where id_table=?";
        try {
            
            pst=cnx.prepareStatement(req);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            if(rs.first())
            {
                return new table(rs.getInt(1), rs.getInt(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(tableservice.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    
  
}
