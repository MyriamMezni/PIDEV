/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Admin;

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
public class AdminService  implements Iservice<Admin>{
    private Connection cnx;
    private PreparedStatement pst;
    private ResultSet rs;

    public AdminService() {
         cnx =Connexion.getInstance().getCnx();
    }
    @Override
    public void insert(Admin t) {
        String req="Insert into user (prenom,nom,email,mdp,image,numTel,role) values (?,?,?,?,?,?,?)";
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, t.getPrenom());
            pst.setString(2, t.getNom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getMDP());
            pst.setString(5, t.getImage());
            pst.setInt(6, t.getNumTel());
            pst.setString(7, "Admin");
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void delete(int id) {
        String req="delete from user where iduser=? and role='Admin'";
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1,id);

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Admin t) {
        String req="update user  set prenom=?,nom=?,email=?,mdp=?,image=?,numTel=? where iduser=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, t.getPrenom());
            pst.setString(2, t.getNom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getMDP());
            pst.setString(5, t.getImage());
            pst.setInt(6, t.getNumTel());
            pst.setInt(7, t.getId_User());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Admin> displayAll() {
        String req="select iduser,prenom,nom,email,mdp,numtel,image from user where role='Admin'";
        List<Admin> list=new ArrayList<>();
        try {
            pst=cnx.prepareStatement(req);
            rs=pst.executeQuery();
            int Id_user;
            String nom;
            String prenom;
            String Email;
            String mdp;
            int numTel;
            String image;
            while(rs.next())
            {
                Id_user=rs.getInt("iduser");
                nom=rs.getString("nom");
                prenom=rs.getString("prenom");
                Email=rs.getString("email");
                mdp=rs.getString("mdp");
                image=rs.getString("image");
                numTel=rs.getInt("numTel");
                list.add(new Admin( Id_user, nom, prenom, Email, mdp, image, numTel));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Admin> rechercheNom(String Nom) {
        String req="select iduser,prenom,nom,email,mdp,numtel,image from user where role='Admin' and nom like ?";
        List<Admin> list=new ArrayList<>();
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, Nom+"%");
            rs=pst.executeQuery();
            int Id_user;
            String nom;
            String prenom;
            String Email;
            String mdp;
            int numTel;
            String image;
            
            while(rs.next())
            {
                Id_user=rs.getInt("iduser");
                nom=rs.getString("nom");
                prenom=rs.getString("prenom");
                Email=rs.getString("email");
                mdp=rs.getString("mdp");
                image=rs.getString("image");
                numTel=rs.getInt("numTel");
                list.add(new Admin( Id_user, nom, prenom, Email, mdp, image, numTel));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Admin authentification(String email,String mdp)
    {
        String req="select iduser,prenom,nom,email,mdp,numtel,image,username,username_canonical,email_canonical,password,roles from user where role='Admin' and username=? and mdp=? and enabled=1";
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, email);
            pst.setString(2, mdp);
            rs=pst.executeQuery();
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
                Admin a=new Admin(Id_user, nom, prenom, Email, mdp2, image, numTel);
                a.setUsername(username);
                a.setUsername_canonical(username_canonical);
                a.setEmail_canonical(email_canonical);
                a.setPasword(password);
                a.setRoles(roles);
                
                return a;
            }
            else 
                return null;
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
            
        
    }
    public boolean existeParEmail(String email)
    {
        String req="select iduser,prenom,nom,email,mdp,numtel,image from user where email_canonical=? ";
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, email);
            rs=pst.executeQuery();
            if(rs.first())
            {
                
                return true;
            }
            else 
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public void majMdp(String email,String mdp,String passwd)
    {
        String req="update user set mdp=?,password=? where email_canonical=? ";
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, mdp);
            pst.setString(2, passwd);
            pst.setString(3, email);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean existe(String email,String mdp,String username)
    {
        String req="select iduser,prenom,nom,email,mdp,numtel,image from user where email_canonical=? or username_canonical=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, email);
            pst.setString(2,username);
            rs=pst.executeQuery();
            if(rs.first())
            {
                
                return true;
            }
            else 
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public String SelectEmail(int id) {
        String req="select * from user where IdUser='"+id+"'";
        String list=null;
        try {
            pst=cnx.prepareStatement(req);
            rs=pst.executeQuery(req);
            int Id_user;
            String nom;
            String prenom;
            String Email;
            String mdp;
            int numTel;
            String image;
            
            while(rs.next())
            {
                Id_user=rs.getInt("iduser");
                nom=rs.getString("nom");
                prenom=rs.getString("prenom");
                Email=rs.getString("email");
                mdp=rs.getString("mdp");
                image=rs.getString("image");
                numTel=rs.getInt("numTel");
                Admin p=new Admin( Id_user, nom, prenom, Email, mdp, image, numTel);
                list=p.getEmail();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
       
    }
    
    
}
