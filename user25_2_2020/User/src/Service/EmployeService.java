/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Employe;
import java.sql.Connection;
import java.sql.Date;
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
 * @author ben younes
 */
public class EmployeService  implements Iservice<Employe>{
    
     private Connection cnx;
    private PreparedStatement pst;
    private ResultSet rs;

    public EmployeService() {
         cnx =Connexion.getInstance().getCnx();
    }
    
    @Override
    public void insert(Employe t) {
        String req="Insert into user (prenom,nom,email,mdp,image,numTel,region,ville,rue,codepostal,datenaissance,salaire,nbheures,typeemploye,role) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, t.getPrenom());
            pst.setString(2, t.getNom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getMDP());
            pst.setString(5, t.getImage());
            pst.setInt(6, t.getNumTel());
            pst.setString(7, t.getRegion());
            pst.setString(8, t.getVille());
            pst.setString(9, t.getRue());
            pst.setString(10, t.getCodePostal());
            pst.setDate(11, t.getDateNaissance());
            pst.setFloat(12, t.getSalaire());
            pst.setInt(13, t.getNbHeures());
            pst.setString(14, t.getType());
            pst.setString(15, "Employe");
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String req="delete from user where iduser=? and role='Employe'";
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1,id);

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Employe t) {
        String req="update user  set prenom=?,nom=?,email=?,mdp=?,image=?,numTel=?,region=?,ville=?,rue=?,codepostal=?,datenaissance=?,salaire=?,nbheures=?,typeemploye=? where iduser=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, t.getPrenom());
            pst.setString(2, t.getNom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getMDP());
            pst.setString(5, t.getImage());
            pst.setInt(6, t.getNumTel());
            pst.setString(7, t.getRegion());
            pst.setString(8, t.getVille());
            pst.setString(9, t.getRue());
            pst.setString(10, t.getCodePostal());
            pst.setDate(11, t.getDateNaissance());
            pst.setFloat(12,t.getSalaire());
            pst.setInt(13, t.getNbHeures());
            pst.setString(14, t.getType());
            pst.setInt(15, t.getId_User());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Employe> displayAll() {
        String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,datenaissance,salaire,nbheures,typeemploye,image from user where role='Employe'";
        List<Employe> list=new ArrayList<>();
        try {
            pst=cnx.prepareStatement(req);
            rs=pst.executeQuery();
            String region;
            String ville;
            String rue;
            String codepostal;
            Date datenaissance;
            float salaire;
            int nbheures;
            String typeemloye;
            int Id_user;
            String nom;
            String prenom;
            String Email;
            String mdp;
            int numTel;
            String image;
            
            while(rs.next())
            {
                region=rs.getString("region");
                ville=rs.getString("ville");
                rue=rs.getString("rue");
                codepostal=rs.getString("codepostal");
                datenaissance=rs.getDate("datenaissance");
                salaire=rs.getFloat("salaire");
                nbheures=rs.getInt("nbheures");
                typeemloye=rs.getString("typeemploye");
                Id_user=rs.getInt("iduser");
                nom=rs.getString("nom");
                prenom=rs.getString("prenom");
                Email=rs.getString("email");
                mdp=rs.getString("mdp");
                image=rs.getString("image");
                numTel=rs.getInt("numTel");
                list.add(new Employe(datenaissance, salaire, nbheures, typeemloye, region, ville, rue, codepostal, Id_user, nom, prenom, Email, mdp, image, numTel));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Employe authentification(String email,String mdp)
    {
        String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,datenaissance,salaire,nbheures,typeemploye,image from user where role='Employe' and email=? and mdp=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, email);
            pst.setString(2, mdp);
            rs=pst.executeQuery();
            String region;
            String ville;
            String rue;
            String codepostal;
            Date datenaissance;
            float salaire;
            int nbheures;
            String typeemloye;
            int Id_user;
            String nom;
            String prenom;
            String Email;
            String mdp2;
            int numTel;
            String image;
            if(rs.first())
            {
                
                region=rs.getString("region");
                ville=rs.getString("ville");
                rue=rs.getString("rue");
                codepostal=rs.getString("codepostal");
                datenaissance=rs.getDate("datenaissance");
                salaire=rs.getFloat("salaire");
                nbheures=rs.getInt("nbheures");
                typeemloye=rs.getString("typeemploye");
                Id_user=rs.getInt("iduser");
                nom=rs.getString("nom");
                prenom=rs.getString("prenom");
                Email=rs.getString("email");
                mdp2=rs.getString("mdp");
                image=rs.getString("image");
                numTel=rs.getInt("numTel");
                return (new Employe(datenaissance, salaire, nbheures, typeemloye, region, ville, rue, codepostal, Id_user, nom, prenom, Email, mdp2, image, numTel));
            }
            else 
                return null;
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
            
        
    }
    @Override
    public List<Employe> rechercheNom(String Nom) {
        String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,datenaissance,salaire,nbheures,typeemploye,image from user where role='Employe' and nom like ?";
        List<Employe> list=new ArrayList<>();
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, Nom+"%");
            rs=pst.executeQuery();
            
            String region;
            String ville;
            String rue;
            String codepostal;
            Date datenaissance;
            float salaire;
            int nbheures;
            String typeemloye;
            int Id_user;
            String nom;
            String prenom;
            String Email;
            String mdp;
            int numTel;
            String image;
            
            while(rs.next())
            {
                region=rs.getString("region");
                ville=rs.getString("ville");
                rue=rs.getString("rue");
                codepostal=rs.getString("codepostal");
                datenaissance=rs.getDate("datenaissance");
                salaire=rs.getFloat("salaire");
                nbheures=rs.getInt("nbheures");
                typeemloye=rs.getString("typeemploye");
                Id_user=rs.getInt("iduser");
                nom=rs.getString("nom");
                prenom=rs.getString("prenom");
                Email=rs.getString("email");
                mdp=rs.getString("mdp");
                image=rs.getString("image");
                numTel=rs.getInt("numTel");
                list.add(new Employe(datenaissance, salaire, nbheures, typeemloye, region, ville, rue, codepostal, Id_user, nom, prenom, Email, mdp, image, numTel));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
}
