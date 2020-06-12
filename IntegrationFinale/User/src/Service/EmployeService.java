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
        String req="Insert into user (prenom,nom,email,mdp,image,numTel,region,ville,rue,codepostal,datenaissance,salaire,nbheures,typeemploye,role,username,username_canonical,email_canonical,password,roles,enabled) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            pst.setString(16, t.getUsername());
            pst.setString(17, t.getUsername_canonical());
            pst.setString(18, t.getEmail_canonical());
            pst.setString(19, t.getPasword());
            pst.setString(20, "a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
            pst.setInt(21, 1);
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
        String req="update user  set prenom=?,nom=?,email=?,mdp=?,image=?,numTel=?,region=?,ville=?,rue=?,codepostal=?,datenaissance=?,salaire=?,nbheures=?,typeemploye=?,username=?,username_canonical=?,email_canonical=?,password=? where iduser=?";
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
            pst.setString(15, t.getUsername());
            pst.setString(16, t.getUsername_canonical());
            pst.setString(17, t.getEmail_canonical());
            pst.setString(18, t.getPasword());
            pst.setInt(19, t.getId_User());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Employe> displayAll() {
        String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,datenaissance,salaire,nbheures,typeemploye,image,enabled from user where role='Employe'";
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
            int enabled;
            
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
                enabled=rs.getInt("enabled");
                list.add(new Employe(datenaissance, salaire, nbheures, typeemloye, region, ville, rue, codepostal, Id_user, nom, prenom, Email, mdp, image, numTel,enabled));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Employe authentification(String email,String mdp)
    {
        String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,datenaissance,salaire,nbheures,typeemploye,image,username,username_canonical,email_canonical,password,roles from user where role='Employe' and username=? and mdp=? and enabled=1";
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
                username=rs.getString("username");
                username_canonical=rs.getString("username_canonical");
                email_canonical=rs.getString("email_canonical");
                password=rs.getString("password");
                roles=rs.getString("roles");
                Employe e=new Employe(datenaissance, salaire, nbheures, typeemloye, region, ville, rue, codepostal, Id_user, nom, prenom, Email, mdp2, image, numTel);
                e.setUsername(username);
                e.setUsername_canonical(username_canonical);
                e.setEmail_canonical(email_canonical);
                e.setPasword(password);
                e.setRoles(roles);
                return e;
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
    public void majEtat(int etat,int id)
    {
        String req="update user set enabled=? where iduser=?";
        try {
            pst=cnx.prepareStatement(req);
            System.out.println(etat);
            pst.setInt(1,etat);
            pst.setInt(2, id);
            pst.executeUpdate();
            //System.out.println("hello");
            
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /*public List<Employe> rechercheNom(String Nom) {
        String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,datenaissance,salaire,nbheures,typeemploye,image from user where type='Employe' and nom like ?";
        List<Employe> list=new ArrayList<>();
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1, Nom+"%");
            rs=pst.executeQuery(req);
            
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
    }*/
    
    public int rechercheParNom(String nom) {
        int s=0;
        try {
            String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,datenaissance,salaire,nbheures,typeemploye,image from user where nom='"+nom+"'";
            pst=cnx.prepareStatement(req);
            //pst.setString(1, Nom+"%");
            rs=pst.executeQuery(req);
            
            String region;
            String ville;
            String rue;
            String codepostal;
            Date datenaissance;
            float salaire;
            int nbheures;
            String typeemloye;
            int Id_user;
            //String nom;
            String prenom;
            String Email;
            String mdp;
            int numTel;
            String image;
            
            while(rs.next())
            {
                
                
                
                
                datenaissance=rs.getDate("datenaissance");
                salaire=rs.getFloat("salaire");
                nbheures=rs.getInt("nbheures");
                typeemloye=rs.getString("typeemploye");
                region=rs.getString("region");
                ville=rs.getString("ville");
                rue=rs.getString("rue");
                codepostal=rs.getString("codepostal");
                Id_user=rs.getInt("iduser");
                nom=rs.getString("nom");
                prenom=rs.getString("prenom");
                Email=rs.getString("email");
                mdp=rs.getString("mdp");
                image=rs.getString("image");
                numTel=rs.getInt("numTel");
                Employe c=new Employe(datenaissance, salaire, nbheures, typeemloye, region, ville, rue, codepostal, Id_user, nom, prenom, Email, mdp, image, numTel);
                s=c.getId_User();
            }
        } catch (SQLException ex) {
            
        }
         return s;
        
   
    
    }
    
    public List<String> listParNom() {
        List<String> list=new ArrayList<>();
        try {
            String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,datenaissance,salaire,nbheures,typeemploye,image from user where TypeEmploye='Babysitter'";
            pst=cnx.prepareStatement(req);
            //pst.setString(1, Nom+"%");
            rs=pst.executeQuery(req);
            
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
                
                
                
                
                datenaissance=rs.getDate("datenaissance");
                salaire=rs.getFloat("salaire");
                nbheures=rs.getInt("nbheures");
                typeemloye=rs.getString("typeemploye");
                region=rs.getString("region");
                ville=rs.getString("ville");
                rue=rs.getString("rue");
                codepostal=rs.getString("codepostal");
                Id_user=rs.getInt("iduser");
                nom=rs.getString("nom");
                prenom=rs.getString("prenom");
                Email=rs.getString("email");
                mdp=rs.getString("mdp");
                image=rs.getString("image");
                numTel=rs.getInt("numTel");
                Employe c=new Employe(datenaissance, salaire, nbheures, typeemloye, region, ville, rue, codepostal, Id_user, nom, prenom, Email, mdp, image, numTel);
                list.add(c.getNom());
            }
        } catch (SQLException ex) {
            
        }
        return list;
   
    
    }
    
    public String rechercheParId(int id) {
        String s="";
        try {
            String req="select iduser,prenom,nom,email,mdp,numtel,region,ville,rue,codepostal,datenaissance,salaire,nbheures,typeemploye,image from user where iduser='"+id+"'";
            pst=cnx.prepareStatement(req);
            //pst.setString(1, Nom+"%");
            rs=pst.executeQuery(req);
            
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
                
                
                
                
                datenaissance=rs.getDate("datenaissance");
                salaire=rs.getFloat("salaire");
                nbheures=rs.getInt("nbheures");
                typeemloye=rs.getString("typeemploye");
                region=rs.getString("region");
                ville=rs.getString("ville");
                rue=rs.getString("rue");
                codepostal=rs.getString("codepostal");
                Id_user=rs.getInt("iduser");
               nom=rs.getString("nom");
                prenom=rs.getString("prenom");
                Email=rs.getString("email");
                mdp=rs.getString("mdp");
                image=rs.getString("image");
                numTel=rs.getInt("numTel");
                Employe c=new Employe(datenaissance, salaire, nbheures, typeemloye, region, ville, rue, codepostal, Id_user, nom, prenom, Email, mdp, image, numTel);
                s=c.getNom()+" "+c.getPrenom();
            }
        } catch (SQLException ex) {
            
        }
         return s;
        
   
    
}

    
}
