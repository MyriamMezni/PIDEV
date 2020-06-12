/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Parent;
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
public class ParentService  implements Iservice<Parent>{
    private Connection cnx;
    private PreparedStatement pst;
    private ResultSet rs;

    public ParentService() {
         cnx =Connexion.getInstance().getCnx();
    }
    
    @Override
    public void insert(Parent t) {
        String req="Insert into user (prenom,nom,email,mdp,image,numTel,region,ville,rue,codepostal,tarif,nbenfant,role,username,username_canonical,email_canonical,password,roles,enabled,datenaissance) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            pst.setString(14, t.getUsername());
            pst.setString(15, t.getUsername_canonical());
            pst.setString(16, t.getEmail_canonical());
            pst.setString(17, t.getPasword());
            pst.setString(18, "a:1:{i:0;s:11:\"ROLE_PARENT\";}");
            pst.setInt(19, 1);
            pst.setDate(20, t.getDateNaissance());
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
        String req="update user  set prenom=?,nom=?,email=?,mdp=?,image=?,numTel=?,region=?,ville=?,rue=?,codepostal=?,tarif=?,nbenfant=?,username=?,username_canonical=?,email_canonical=?,password=?,datenaissance=? where iduser=?";
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
            pst.setString(13, t.getUsername());
            pst.setString(14, t.getUsername_canonical());
            pst.setString(15, t.getEmail_canonical());
            pst.setString(16, t.getPasword());
            pst.setDate(17, t.getDateNaissance());
            pst.setInt(18, t.getId_User());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Parent> displayAll() {
        String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,tarif,nbenfant,image,enabled from user where role='Parent'";
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
            int enabled;
            
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
                enabled=rs.getInt("enabled");
                list.add(new Parent(region, ville, rue, codepostal, tarif, nbEnfant, Id_user, nom, prenom, Email, mdp, image, numTel,enabled));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Parent> rechercheNom(String Nom) {
        String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,tarif,nbenfant,image,enabled from user where role='Parent' and nom like ?";
        List<Parent> list=new ArrayList<>();
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, "%"+Nom+"%");
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
            int enabled;
            
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
                enabled=rs.getInt("enabled");
                Parent pL=new Parent(region, ville, rue, codepostal, tarif, nbEnfant, Id_user, nom, prenom, Email, mdp, image, numTel);
                pL.setBloque(enabled);
                list.add(pL);
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
        String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,datenaissance,tarif,nbenfant,image,username,username_canonical,email_canonical,password,roles from user where role='Parent' and username=? and mdp=? and enabled=1";
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
            float tarif;
            int nbEnfant;
            int Id_user;
            String nom;
            String prenom;
            String Email;
            String mdp2;
            int numTel;
            String image;
            String username;
            String username_canonical;
            String email_canonical;
            String password;
            String roles;
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
                username=rs.getString("username");
                username_canonical=rs.getString("username_canonical");
                email_canonical=rs.getString("email_canonical");
                password=rs.getString("password");
                roles=rs.getString("roles");
                datenaissance=rs.getDate("datenaissance");
                Parent p=new Parent(region, ville, rue, codepostal, tarif, nbEnfant, Id_user, nom, prenom, Email, mdp2, image, numTel);
                p.setDateNaissance(datenaissance);
                p.setUsername(username);
                p.setUsername_canonical(username_canonical);
                p.setEmail_canonical(email_canonical);
                p.setPasword(password);
                p.setRoles(roles);
                return p;
            }
            else 
                return null;
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
            
        
    }
    public void majEtat(int etat,int id)
    {
        String req="update user set enabled=? where iduser=?";
        try {
            pst=cnx.prepareStatement(req);
            System.out.println(etat);
            pst.setInt(1,etat);
            pst.setInt(2, id);
            pst.executeUpdate();
            System.out.println("hello");
            
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<String> displayAllParId() {
        String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,tarif,nbenfant,image from user where TypeEmploye='Parent'";
        List<String> list=new ArrayList<>();
        try {
            pst=cnx.prepareStatement(req);
            rs=pst.executeQuery(req);
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
                Parent p=new Parent(region, ville, rue, codepostal, tarif, nbEnfant, Id_user, nom, prenom, Email, mdp, image, numTel);
               String z=p.getNom();
               list.add(z);
              // System.out.println(list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public int selectParNom(String nom) {
        String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,tarif,nbenfant,image from user where nom='"+nom+"'";
         int s = 0;
        try {
            pst=cnx.prepareStatement(req);
            rs=pst.executeQuery(req);
            String region;
            String ville;
            String rue;
            String codepostal;
            float tarif;
            int nbEnfant;
            int Id_user;
          //  String nom;
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
                Parent p=new Parent(region, ville, rue, codepostal, tarif, nbEnfant, Id_user, nom, prenom, Email, mdp, image, numTel);
                s=p.getId_User();
               
              // System.out.println(list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
}
