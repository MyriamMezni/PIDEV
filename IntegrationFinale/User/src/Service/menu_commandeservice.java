/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

//import entity.Menu;
import entity.MenuCommande;
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
public class menu_commandeservice /*implements iservice<MenuCommande>*/ {
    
         private Connection cnx;
 
    private PreparedStatement pst;
    private ResultSet rs;
    
    
    public menu_commandeservice(){
        cnx=Connexion.getInstance().getCnx();
    }

    //@Override
    public void insert(MenuCommande mc) {
        
                       String req="insert into menu_commande (id_menu,id_enfant,jour_de_la_semaine) values (?,?,?)";
        
        try {
           
            pst=cnx.prepareStatement(req);
            
           // pst.setInt(1,ch.getId_enfant());
            
            pst.setInt(1,mc.getM().getId());
            pst.setInt(2,mc.getId_enfant());

            pst.setString(3,mc.getJour_de_la_semaine());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //@Override
    public void update(MenuCommande mc) {
                       String req="update menu_commande set jour_de_la_semaine=? where id_enfant=? and id_menu=?";
        try {
            
            pst=cnx.prepareStatement(req);
            pst.setInt(2,mc.getId_enfant());
            pst.setInt(3,mc.getM().getId());
          //  pst.setInt(1,ch.getId_enfant());
            pst.setString(1,mc.getJour_de_la_semaine());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public void delete(int id_menu,int id_enfant) {
         String req="delete from menu_commande where id_menu=? and id_enfant=?";
                  try {
            
            pst=cnx.prepareStatement(req);
            pst.setInt(1,id_menu);
            pst.setInt(2,id_enfant);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //@Override
    public List<MenuCommande> displayAll() {
            String req="select * from menu_commande";
            List<MenuCommande> list=new ArrayList<>();
            manuservice ms=new manuservice();
            try {
            pst=cnx.prepareStatement(req);
            rs=pst.executeQuery();
            while(rs.next()){
                list.add(new MenuCommande(ms.rechercher_menuId(rs.getInt(1)), rs.getInt(2), rs.getString(3)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
            return list;
    }
    public boolean existe(int id_enfant,int id_menu)
    {
        String req="select * from menu_commande where id_menu=? and id_enfant=?";
             try {
                pst=cnx.prepareStatement(req);
                pst.setInt(1, id_menu);
                pst.setInt(2, id_enfant);
                rs=pst.executeQuery();
                if(rs.first())
                    return true;
                else
                    return false;
             } catch (SQLException ex) {
                 Logger.getLogger(menu_commandeservice.class.getName()).log(Level.SEVERE, null, ex);
                 return false;
             }
       
    }
    public boolean existeEnfantEtJour(int id_enfant,String jour)
    {
        String req="select * from menu_commande where id_enfant=? and jour_de_la_semaine like ?";
             try {
                pst=cnx.prepareStatement(req);
                pst.setInt(1, id_enfant);
                pst.setString(2, "%"+jour+"%");
                rs=pst.executeQuery();
                if(rs.first())
                    return true;
                else
                    return false;
             } catch (SQLException ex) {
                 Logger.getLogger(menu_commandeservice.class.getName()).log(Level.SEVERE, null, ex);
                 return false;
             }
       
    }
    public boolean existetout(int id_enfant,int id_menu,String jour)
    {
        String req="select * from menu_commande where id_enfant=? and jour_de_la_semaine like ? and id_menu=?";
        try {
                pst=cnx.prepareStatement(req);
                pst.setInt(1, id_enfant);
                pst.setString(2, "%"+jour+"%");
                pst.setInt(3, id_menu);
                rs=pst.executeQuery();
                if(rs.first())
                    return true;
                else
                    return false;
             } catch (SQLException ex) {
                 Logger.getLogger(menu_commandeservice.class.getName()).log(Level.SEVERE, null, ex);
                 return false;
             }
    }
    public String jourDeSemaine(int id_enfant,String jour)
    {
        String req="select jour_de_la_semaine from menu_commande where id_enfant=? and jour_de_la_semaine like ?";
             try {
                 pst=cnx.prepareStatement(req);
                 pst.setInt(1, id_enfant);
                 pst.setString(2, "%"+jour+"%");
                 rs=pst.executeQuery();
                 if(rs.first())
                 {
                     return rs.getString(1);
                 }
                 else
                 {
                     return null;
                 }
             } catch (SQLException ex) {
                 Logger.getLogger(menu_commandeservice.class.getName()).log(Level.SEVERE, null, ex);
                 return null;
             }
    }
    public void updateJourDeSamine(int id_enfant,String jour,String nouvelleValeur) {
        String req="update menu_commande set jour_de_la_semaine=? where id_enfant=? and jour_de_la_semaine like ?";
        try {
            
            pst=cnx.prepareStatement(req);
            pst.setInt(2,id_enfant);
            pst.setString(3,"%"+jour+"%");
          //  pst.setInt(1,ch.getId_enfant());
          /*String j=jourDeSemaine(id_enfant, jour);
          j=j.replace(jour, "");*/
            //System.out.println(j);
            pst.setString(1,nouvelleValeur);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(chaiseservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public String getJours(int id_enfant,int id_menu)
    {
        String req="select jour_de_la_semaine from menu_commande where id_enfant=? and id_menu=?";
             try {
                 pst=cnx.prepareStatement(req);
                 pst.setInt(1, id_enfant);
                 pst.setInt(2, id_menu);
                 rs=pst.executeQuery();
                 if(rs.first())
                 {
                     return rs.getString(1);
                 }
                 else
                 {
                     return null;
                 }
             } catch (SQLException ex) {
                 Logger.getLogger(menu_commandeservice.class.getName()).log(Level.SEVERE, null, ex);
                 return null;
             }
    }
    public int getStat(int id_menu)
    {
        String req="select sum(length(jour_de_la_semaine)) from menu_commande where id_menu=?";
             try {
                 pst=cnx.prepareStatement(req);
                 pst.setInt(1, id_menu);
                 rs=pst.executeQuery();
                 if(rs.first())
                 {
                     return rs.getInt(1);
                 }
                 else return 0;
             } catch (SQLException ex) {
                 Logger.getLogger(menu_commandeservice.class.getName()).log(Level.SEVERE, null, ex);
                 return 0;
             }
    }
    public int getTotal()
    {
        String req="select sum(length(jour_de_la_semaine)) from menu_commande";
             try {
                 pst=cnx.prepareStatement(req);
                 
                 rs=pst.executeQuery();
                 if(rs.first())
                 {
                     return rs.getInt(1);
                 }
                 else return 0;
             } catch (SQLException ex) {
                 Logger.getLogger(menu_commandeservice.class.getName()).log(Level.SEVERE, null, ex);
                 return 0;
             }
    }
    public int getMenuPourMail(String prenom,String jour)
    {
        String req="SELECT menu_commande.id_menu from menu_commande join enfant on enfant.id_enfant=menu_commande.id_enfant WHERE enfant.prenom=? and jour_de_la_semaine like ?";
        try {
                 pst=cnx.prepareStatement(req);
                 pst.setString(1, prenom);
                 pst.setString(2, "%"+jour+"%");
                 rs=pst.executeQuery();
                 if(rs.first())
                 {
                     return rs.getInt(1);
                 }
                 else return 0;
             } catch (SQLException ex) {
                 Logger.getLogger(menu_commandeservice.class.getName()).log(Level.SEVERE, null, ex);
                 return 0;
             }
    }
    
    
    
}
