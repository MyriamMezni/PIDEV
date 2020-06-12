/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Babysitting;
import entity.Enfant;
import util.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HEDI MSELMI
 */
public class ServiceBabysitting implements IServiceEnfant<Babysitting> {
private Connection con;
    private Statement ste;
    
    
    public ServiceBabysitting() {
        con = Connexion.getInstance().getCnx();

    }
    @Override
    public void ajouter(Babysitting t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("INSERT INTO `pidev`.`babysitter` ( `idBabysitter`,`heureDebut`, `heureFin`, `jourSemaine`, `prixHeure`, `id_enfant`) VALUES (?, ?, ?, ?, ?, ?);" );
       
    pre.setInt(1, t.getIdBabysitter());
    pre.setInt(2, t.getHeureDebut());
    pre.setInt(3, t.getHeureFin());
    pre.setString(4, t.getJourSemaine());
    pre.setFloat(5, t.getPrixHeure());
    pre.setInt(6, t.getId_enfant().getId_enfant());
    
    
    pre.executeUpdate();
    }

    @Override
    public boolean delete(int idBabysitter) throws SQLException {
      String req="DELETE FROM `babysitter` WHERE idBabysitter= ?";
        boolean rowDeleted=false;
        PreparedStatement statement;
        try {
            statement = con.prepareStatement(req);
            statement.setInt(1, idBabysitter);
            rowDeleted= statement.executeUpdate() == idBabysitter;
            
            return rowDeleted;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      return rowDeleted;
    }

    @Override
    public boolean update(Babysitting t, int id) throws SQLException {
        
        
        {
        try{
            //String req="update Cours set Nom='"+Nom+"' where idC='"+id+"'";
             //String req="update Cours set Nom='"+t.setNom(t.getNom())+"',Catégorie='"+t.setCatégorie(t.getCatégorie())+"',Description='"+t.setDescription(t.get)+"',contenu='"+t.setContenu()+"' where Cours.idC="+t.getIdC();      
            String req="update babysitter set heureDebut='"+t.getHeureDebut()+"',heureFin='"+t.getHeureFin()+"',jourSemaine='"+t.getJourSemaine()+"',prixHeure='"+t.getPrixHeure()+"',id_enfant='"+t.getId_enfant().getId_enfant()+"'   where idBabysitter='"+id+"'";
            ste=con.createStatement();
             ste.executeUpdate(req);
       }
        catch (SQLException ex) {
            Logger.getLogger(ServiceBabysitting.class.getName()).log(Level.SEVERE, null, ex);
        } //To change body of generated methods, choose Tools | Templates. //To change body of generated methods, choose Tools | Templates.
    }
        return false;
    
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Babysitting> readAll() throws SQLException {
        List<Babysitting> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from babysitter");
     while (rs.next()) {                
               int idBabysitter=rs.getInt(1);
               int heureDebut=rs.getInt("heureDebut");
               int heureFin=rs.getInt("heureFin");
               String jourSemaine=rs.getString("jourSemaine");
               int prixHeure=rs.getInt("prixHeure");
               Enfant id_enfant=new Enfant(rs.getInt("id_enfant"));    
               Babysitting p;
            p = new Babysitting(idBabysitter, heureDebut, heureFin, jourSemaine, prixHeure, id_enfant);
     arr.add(p);
     }
    return arr; //To change body of generated methods, choose Tools | Templates.
    }
    public List<Babysitting> readbyId(int id) throws SQLException{
        List<Babysitting> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from babysitter where idBabysitter='"+id+"'" );
     while (rs.next()) {                
               int idBabysitter=rs.getInt(1);
               int heureDebut=rs.getInt("heureDebut");
               int heureFin=rs.getInt("heureFin");
               String jourSemaine=rs.getString("jourSemaine");
               int prixHeure=rs.getInt("prixHeure");
               Enfant id_enfant=new Enfant(rs.getInt("id_enfant"));    
               Babysitting p;
            p = new Babysitting(idBabysitter, heureDebut, heureFin, jourSemaine, prixHeure, id_enfant);
     arr.add(p);
     }
    return arr;
    }
    
    
}
