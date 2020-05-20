/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import Service.AdminService;
import Service.EmployeService;
import Service.ParentService;
import entity.Admin;
import entity.Employe;
import entity.Parent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
import util.JavaMailUtil;

/**
 *
 * @author ben younes
 */
public class User {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*Parent p1=new Parent("Ben Arous3", "BoumHal3", "Babylon3", "10023", 300, 10,2 , "test5", "prenom5", "Email5", "MDP5", "Image5", 55555555);
        Parent p2=new Parent("Ben Arous2", "BoumHal2", "Babylon2", "10022", 500, 1,12 , "Nom2", "prenom2", "Email2", "MDP2", "Image2", 33333333);
        Date d=new Date(1998-1900, 11, 7);
        Employe e1=new Employe(d,550 , 60, "Babysitter", "Tunis", "Mont Plaisir", "Nue", "1003", 4, "NomE1", "PrenomE1", "EmailE1", "MDPE1", "ImageE1", 11111111);
        Employe e2=new Employe(d,550 , 60, "Cantine", "Tunis", "Mont Plaisir", "Nue", "1004", 4, "testE2", "PrenomE2", "EmailE2", "MDPE2", "ImageE2", 21212121);
        Admin a1=new Admin(7, "nomA2", "prenomA2", "EmailA2", "MDPA2","ImageA2", 98989898);
        ParentService ps=new ParentService();
        EmployeService es=new EmployeService();
        AdminService as=new AdminService();*/
        /*ps.insert(p1);
        ps.insert(p2);*/
       
      /* es.insert(e1);
       es.insert(e2);*/
        /*System.out.println("Admin");
        for(Admin a:as.displayAll())
       {
           System.out.println(a);
       }
        System.out.println("Parent");
      for(Parent p:ps.displayAll())
       {
           System.out.println(p);
       }
        System.out.println("Employe");
      for(Employe e:es.displayAll())
       {
           System.out.println(e);
       }
        System.out.println("Recherche parent");
      for(Parent p:ps.rechercheNom("No"))
      {
          System.out.println(p);
      }
      
      System.out.println("Recherche Admin");
      for(Admin a:as.rechercheNom("NO"))
      {
          System.out.println(a);
      }
      
      System.out.println("Recherche Employe");
      for(Employe e:es.rechercheNom("No"))
      {
          System.out.println(e);
      }
      
      System.out.println("Tri parent tarif");
      for(Parent p:ps.triTarif())
      {
          System.out.println(p);
      }*/
        /*ParentService ps=new ParentService();
        System.out.println(ps.authentification("ok@ok.com", "5555"));*/
        //JavaMailUtil.SendMail("mohamednaim.benyounes@esprit.tn", "555555");
        Random r=new Random();
        String ran="";
        String alphabet="123456789abcdefghijklmnopqrstuvwxzyABCDEFGHIJKLMNOPQRSTUVWXYZ:?!#";
        for(int i=0;i<15;i++)
        {
            /*rando=r.nextInt(128);
            System.out.println(rando);
            ran+=(char)rando*/
            ran+=alphabet.charAt(r.nextInt(alphabet.length()));
            //System.out.println(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        
        System.out.println(ran);
    }
    
}
