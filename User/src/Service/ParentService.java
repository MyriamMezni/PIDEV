/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Parent;
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
 * @author ben younes
 */
public class ParentService  implements Iservice<Parent>{
    private Connection cnx;
    private PreparedStatement pst;
    private ResultSet rs;

    public ParentService() {
         cnx =Connexion.getInstance().getCnx();
    }
    
    @Override
    public void insert(Parent t) {
        String req="Insert into user (prenom,nom,email,mdp,image,numTel,region,ville,rue,codepostal,tarif,nbenfant,role) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            pst.setFloat(11, t.getTarif());
            pst.setInt(12, t.getNbEnfant());
            pst.setString(13, "Parent");
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String req="delete from user where iduser=? and role='Parent'";
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1,id);

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Parent t) {
        String req="update user  set prenom=?,nom=?,email=?,mdp=?,image=?,numTel=?,region=?,ville=?,rue=?,codepostal=?,tarif=?,nbenfant=? where iduser=?";
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
            pst.setFloat(11, t.getTarif());
            pst.setInt(12, t.getNbEnfant());
            pst.setInt(13, t.getId_User());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Parent> displayAll() {
        String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,tarif,nbenfant,image from user where role='Parent'";
        List<Parent> list=new ArrayList<>();
        try {
            pst=cnx.prepareStatement(req);
            rs=pst.executeQuery();
            String region;
            String ville;
            String rue;
            String codepostal;
            float tarif;
            int nbEnfant;
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
                tarif=rs.getFloat("tarif");
                nbEnfant=rs.getInt("nbEnfant");
                Id_user=rs.getInt("iduser");
                nom=rs.getString("nom");
                prenom=rs.getString("prenom");
                Email=rs.getString("email");
                mdp=rs.getString("mdp");
                image=rs.getString("image");
                numTel=rs.getInt("numTel");
                list.add(new Parent(region, ville, rue, codepostal, tarif, nbEnfant, Id_user, nom, prenom, Email, mdp, image, numTel));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Parent> rechercheNom(String Nom) {
        String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,tarif,nbenfant,image from user where role='Parent' and nom like ?";
        List<Parent> list=new ArrayList<>();
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, Nom+"%");
            rs=pst.executeQuery();
            
            String region;
            String ville;
            String rue;
            String codepostal;
            float tarif;
            int nbEnfant;
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
                tarif=rs.getFloat("tarif");
                nbEnfant=rs.getInt("nbEnfant");
                Id_user=rs.getInt("iduser");
                nom=rs.getString("nom");
                prenom=rs.getString("prenom");
                Email=rs.getString("email");
                mdp=rs.getString("mdp");
                image=rs.getString("image");
                numTel=rs.getInt("numTel");
                list.add(new Parent(region, ville, rue, codepostal, tarif, nbEnfant, Id_user, nom, prenom, Email, mdp, image, numTel));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Parent> triTarif() {
        String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,tarif,nbenfant,image from user where role='Parent' ORDER BY tarif desc";
        List<Parent> list=new ArrayList<>();
        try {
            pst=cnx.prepareStatement(req);
            rs=pst.executeQuery();
            String region;
            String ville;
            String rue;
            String codepostal;
            float tarif;
            int nbEnfant;
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
                tarif=rs.getFloat("tarif");
                nbEnfant=rs.getInt("nbEnfant");
                Id_user=rs.getInt("iduser");
                nom=rs.getString("nom");
                prenom=rs.getString("prenom");
                Email=rs.getString("email");
                mdp=rs.getString("mdp");
                image=rs.getString("image");
                numTel=rs.getInt("numTel");
                list.add(new Parent(region, ville, rue, codepostal, tarif, nbEnfant, Id_user, nom, prenom, Email, mdp, image, numTel));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Parent authentification(String email,String mdp)
    {
        String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,tarif,nbenfant,image from user where role='Parent' and email=? and mdp=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, email);
            pst.setString(2, mdp);
            rs=pst.executeQuery();
            String region;
            String ville;
            String rue;
            String codepostal;
            float tarif;
            int nbEnfant;
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
                tarif=rs.getFloat("tarif");
                nbEnfant=rs.getInt("nbEnfant");
                Id_user=rs.getInt("iduser");
                nom=rs.getString("nom");
                prenom=rs.getString("prenom");
                Email=rs.getString("email");
                mdp2=rs.getString("mdp");
                image=rs.getString("image");
                numTel=rs.getInt("numTel");
                return (new Parent(region, ville, rue, codepostal, tarif, nbEnfant, Id_user, nom, prenom, Email, mdp2, image, numTel));
            }
            else 
                return null;
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
            
        
    }
    
}
