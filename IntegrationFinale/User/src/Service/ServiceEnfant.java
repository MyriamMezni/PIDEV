/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Employe;
import entity.Enfant;
import entity.User;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import util.Connexion;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableView;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author House
 */
public class ServiceEnfant implements IServiceEnfant<Enfant> {

    private Connection con;
    private Statement ste;
    private TableView<Enfant> table;

    public ServiceEnfant() {
        con = Connexion.getInstance().getCnx();

    }

    public ServiceEnfant(TableView<Enfant> table) {
      this.table=table;
    }

   

    @Override
   public void ajouter(Enfant t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `pidev`. `enfant (`id_enfant`, `nom`, `prenom`, `datenaissance`, `remarque`, `image`,`id_parent`,`cantine` ) VALUES (NULL, '" + t.getId_enfant()+ "','" + t.getNom() + "', '" + t.getPrenom() + "', '" + t.getDatenaissance() + "' ,'" + t.getRemarque() + "','" + t.getImage()+ "','" + t.getId_parent()+ "','" +t.getCantine()+ "'); ";
        ste.executeUpdate(requeteInsert);
        
        
    }
    public void ajouter1(Enfant p) throws SQLException
    {
                    
        PreparedStatement pre=con.prepareStatement("INSERT INTO `pidev`.`enfant` ( `id_enfant`,`nom`, `prenom`, `datenaissance`, `remarque`, `image`, `id_parent`, `cantine`, `document`,`status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);" );
       
    pre.setInt(1, p.getId_enfant());
    pre.setString(2, p.getNom());
    pre.setString(3, p.getPrenom());
    pre.setDate(4, p.getDatenaissance());
    pre.setString(5, p.getRemarque());
    int contine;
    if(p.getCantine().equals("oui"))
    {
        contine=1;
                
    }
    else
    {
        contine=0;
    }
    pre.setString(6, p.getImage());
        System.out.println(p.getId_parent().getId_User());
    pre.setInt(7,p.getId_parent().getId_User());
    pre.setInt(8,contine);
    pre.setString(9, p.getDocument());
    pre.setString(10, p.getStatus());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(int id) throws SQLException {
        String req="DELETE FROM  enfant where id_enfant = ?";
        boolean rowDeleted=false;
        PreparedStatement statement;
        try {
            statement = con.prepareStatement(req);
            statement.setInt(1, id);
            rowDeleted= statement.executeUpdate() == id;
            
            return rowDeleted;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      return rowDeleted; //To change body of generated methods, choose Tools | Templates.
    }

    

   
    
   
   
   

    @Override
    public List<Enfant> readAll() throws SQLException {
        List<Enfant> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from enfant");
     while (rs.next()) {                
               int id_enfant=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               Date datenaissance=rs.getDate("datenaissance");
               String remarque=rs.getString("remarque");
               String image=rs.getString("image");
               User id_parent= new User(rs.getInt("id_parent"));
                String cantine=rs.getString("cantine");
                String document=rs.getString("document");
                String status=rs.getString("status");
               Enfant p;
            p = new Enfant(id_enfant, nom, prenom, datenaissance, remarque, image, id_parent, cantine,document,status);
     arr.add(p);
     }
    return arr; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Enfant t, int id) throws SQLException {
        {
        try{  
            //String req="update Cours set Nom='"+Nom+"' where idC='"+id+"'";
             //String req="update Cours set Nom='"+t.setNom(t.getNom())+"',Catégorie='"+t.setCatégorie(t.getCatégorie())+"',Description='"+t.setDescription(t.get)+"',contenu='"+t.setContenu()+"' where Cours.idC="+t.getIdC();      
            int cantine=0;
             if(t.getCantine().equals("oui") || t.getCantine().equals("1"))
             {
                 cantine=1;
                 
             }
             if(t.getCantine().equals("non") || t.getCantine().equals("0"))
             {
                 cantine=0;
             }
             System.out.println(cantine);
             String req="update enfant set nom='"+t.getNom()+"',prenom='"+t.getPrenom()+"',datenaissance='"+t.getDatenaissance()+"',remarque='"+t.getRemarque()+"',image='"+t.getImage()+"',cantine='"+cantine+"',status='"+t.getStatus()+"'   where id_enfant='"+id+"'";
            ste=con.createStatement();
             ste.executeUpdate(req);
       }
        catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
        } //To change body of generated methods, choose Tools | Templates. //To change body of generated methods, choose Tools | Templates.
    }
        return false;
}
    public List<Integer> reponse() throws SQLException {
        List<Integer> t=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from enfant");
     while (rs.next()) {                
               int id_enfant=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               Date datenaissance=rs.getDate("datenaissance");
               String remarque=rs.getString("remarque");
               String image=rs.getString("image");
               User id_parent= new User(rs.getInt("id_parent"));
                String cantine=rs.getString("cantine");
                 String document=rs.getString("document");
                String status="en attente";
               Enfant p;
            p = new Enfant(id_enfant, nom, prenom, datenaissance, remarque, image, document, id_parent, cantine);
            int z=p.getId_parent().getId_User();
            t.add(z);
     }
    return t; //To change body of generated methods, choose Tools | Templates.
    }
    public List<Enfant> afficheParID(int id) throws SQLException {
        List<Enfant> t=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from enfant where id_parent='"+id+"'");
     while (rs.next()) {                
               int id_enfant=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               Date datenaissance=rs.getDate("datenaissance");
               String remarque=rs.getString("remarque");
               String image=rs.getString("image");
               User id_parent= new User(rs.getInt("id_parent"));
                String cantine=rs.getString("cantine");
                String document=rs.getString("document"); 
               Enfant p;
            p = new Enfant(id_enfant, nom, prenom, datenaissance, remarque, image, document, id_parent, cantine);
            
            t.add(p);
     }
    return t; //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<String> display() throws SQLException {
        List<String> t=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from enfant");
     while (rs.next()) {                
               int id_enfant=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               Date datenaissance=rs.getDate("datenaissance");
               String remarque=rs.getString("remarque");
               String image=rs.getString("image");
               User id_parent= new User(rs.getInt("id_parent"));
                String cantine=rs.getString("cantine"); 
                String document=rs.getString("document");
                
               Enfant p;
            p = new Enfant(id_enfant, nom, prenom, datenaissance, remarque, image, document, id_parent, cantine);
            
            t.add(p.getNom());
     }
    return t; //To change body of generated methods, choose Tools | Templates.
    }
    
     public Integer AfficheParNom(String nom) throws SQLException {
        int a=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from enfant where nom='"+nom+"'");
     while (rs.next()) {                
               int id_enfant=rs.getInt(1);
            //   String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               Date datenaissance=rs.getDate("datenaissance");
               String remarque=rs.getString("remarque");
               String image=rs.getString("image");
               User id_parent= new User(rs.getInt("id_parent"));
                String cantine=rs.getString("cantine"); 
                String document=rs.getString("document");
                
               Enfant p;
            p = new Enfant(id_enfant, nom, prenom, datenaissance, remarque, image, document, id_parent, cantine);
            
            a=p.getId_enfant();
     }
    return a; //To change body of generated methods, choose Tools | Templates.
    }
     
     public String Selectpourmail(int id) throws SQLException {
        String a=null;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from enfant where id_enfant='"+id+"'");
     while (rs.next()) {                
               int id_enfant=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               Date datenaissance=rs.getDate("datenaissance");
               String remarque=rs.getString("remarque");
               String image=rs.getString("image");
               User id_parent= new User(rs.getInt("id_parent"));
                String cantine=rs.getString("cantine"); 
                String document=rs.getString("document");
                
               Enfant p;
            p = new Enfant(id_enfant, nom, prenom, datenaissance, remarque, image, document, id_parent, cantine);
            
            a=p.getId_parent().getEmail();
     }
    return a; //To change body of generated methods, choose Tools | Templates.
    }
    public int getLast()
    {
        String req="SELECT * FROM enfant ORDER BY id_enfant DESC LIMIT 1";
        try {
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery(req);
            if(rs.first())
                return rs.getInt("id_enfant");
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
    }
    public List<String> getNomParParentAvecCantine(int id_Parent)
    {
        List<String> arr=new ArrayList<>();
        String req="Select prenom from enfant where cantine=1 and id_parent="+id_Parent;
        try {
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery(req);
            while(rs.next())
            {
                arr.add(rs.getString("prenom"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    public List<String> getNomParParentSansCantine(int id_Parent)
    {
        List<String> arr=new ArrayList<>();
        String req="Select prenom from enfant where cantine=0 and id_parent="+id_Parent;
        try {
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery(req);
            while(rs.next())
            {
                arr.add(rs.getString("prenom"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    public int getIdParPrenomEtParent(int id_parent,String prenom)
    {
        String req="Select id_enfant from enfant where prenom='"+prenom+"' and id_parent="+id_parent;
        try {
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery(req);
            if(rs.first())
            {
                return rs.getInt("id_enfant");
            }
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    public void setCantine(int i,int id)
    {
        String req="update enfant set cantine="+i+" where id_enfant="+id;
        try {
            ste=con.createStatement();
            ste.executeUpdate(req);
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    
    
   public String AfficheLeNom(int id_enfant) throws SQLException {
        String a="";
        int b=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from enfant where id_enfant='"+id_enfant+"'");
     while (rs.next()) {                
              // int id_enfant=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               Date datenaissance=rs.getDate("datenaissance");
               String remarque=rs.getString("remarque");
               String image=rs.getString("image");
               User id_parent= new User(rs.getInt("id_parent"));
                int cantine=rs.getInt("cantine"); 
                String document=rs.getString("document");
                
               Enfant p;
            p = new Enfant(id_enfant, nom, prenom, datenaissance, remarque, image, document, id_parent, Integer.toString(cantine));
            
            a=p.getNom();
     }
    return a; //To change body of generated methods, choose Tools | Templates.
       
    }
   
    
}
