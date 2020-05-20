/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Babysitting;
import com.mycompany.myapp.entities.Enfant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HEDI MSELMI
 */
public class ServiceBabysitting {
     public ArrayList<Enfant> tasks;
    
    public static ServiceBabysitting instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceBabysitting() {
         req = new ConnectionRequest();
    }

    public static ServiceBabysitting getInstance() {
        if (instance == null) {
            instance = new ServiceBabysitting();
        }
        return instance;
    }

    public boolean AjoutBabysitter(Babysitting t,int id) {
      ConnectionRequest con = new ConnectionRequest();
SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");// création d'une nouvelle demande de connexion   id_reclamation="+id_reclamation+"&description="+description
         String jour="lundi";
        String Url = "http://localhost/Pidev/web/app_dev.php/Pidev/json/ajout/"+t.getIdBabysitter()+"?heuredebut="+t.getHeureDebut()/60+"&heurefin="+t.getHeureFin()/60+"&joursemaine="+t.getJourSemaine()+"&prixheure="+t.getPrixHeure();  
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
        //    System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return false; // Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }

   public ArrayList<Babysitting> parseListReclamationJson(String json) {

        ArrayList<Babysitting> listReclamations = new ArrayList<>();

      try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> reclamations = j.parseJSON(new CharArrayReader(json.toCharArray()));
       
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamations.get("root");
            

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Babysitting r = new Babysitting();
               Babysitting r1 = new Babysitting();
              //  int id_enfant = Integer.parseInt(obj.get("id_enfant").toString());
             //   r.setId_enfant(1) ;  	
           //  float idBabysitter = Float.parseFloat(obj.get("idbabysitter").toString());
       //    naimp2  NaimP2
      //  System.out.println("aa:"+obj.get("idbabysitter").toString());
          if(obj.get("idbabysitter").toString().equals("{prenom=Mohamed Naim, nom=Younes, image=50592.jpeg, numtel=5.5023485E7, datenaissance={timezone={name=UTC, location={country_code=??, latitude=0.0, longitude=0.0, comments=}}, offset=0.0, timestamp=6.31152E8}, salaire=500.0, region=Ben Arous, ville=boumhal, rue=3rue babel, codepostal=1002, nbheures=20.0, typeemploye=BabySitter, role=Employe, id=15.0, username=NaimE2, usernameCanonical=naime2, email=mohamebenyounes@gmail.com, emailCanonical=mohamebenyounes@gmail.com, password=$2y$13$tXHTJicfvOA3yb3ux4KBVuxCM1d/5PusgfqL6b6pZ.ifDWWPI58Ze, lastLogin={timezone={name=UTC, location={country_code=??, latitude=0.0, longitude=0.0, comments=}}, offset=0.0, timestamp=1.587112388E9}, roles=[ROLE_ADMIN, ROLE_USER], accountNonExpired=true, accountNonLocked=true, credentialsNonExpired=true, enabled=true, superAdmin=false, groups=[], groupNames=[]}"))
          {  r.setIdBabysitter(15); }

              else
                   {r.setIdBabysitter(16);  }
                  float heuredebut = Float.parseFloat(obj.get("heuredebut").toString());
                r.setHeureDebut((int) heuredebut);
                   float heurefin = Float.parseFloat(obj.get("heurefin").toString());
                r.setHeureFin((int) heurefin);
                 
                
                
              
                   
               
                r.setJourSemaine(obj.get("joursemaine").toString());
                 float prixHeure = Float.parseFloat(obj.get("prixheure").toString());
               r.setPrixHeure((int) prixHeure) ;
              //    com.mycompany.Entite.Enfant s;
                 // float id_enfant = Float.parseFloat(obj.get("idEnfant").toString());
              // r.setId_enfant();
                Enfant sp=new Enfant(ServiceEnfant.getInstance().getListReclamations().get(5).getId_enfant());
                r.setId_enfant(sp);
              //  System.out.println("test......"+Float.parseFloat(obj.get("id_enfant").toString()));
                
                listReclamations.add(r);

            }
              } catch (IOException ex) {
            
         }

     
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
     //   System.out.println(listReclamations);
        return listReclamations;
    }
    
      ArrayList<Babysitting> listReclamations= new ArrayList<>();
    
    public ArrayList<Babysitting> getListReclamations(){       
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/Pidev/web/app_dev.php/Pidev/ttt";
        con.setUrl(url);
         con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceBabysitting ser = new ServiceBabysitting();
                listReclamations = ser.parseListReclamationJson(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReclamations;
    }
    
     public void SupprimerBabysitting(int id) {
    ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Pidev/web/app_dev.php/Pidev/remove/"+id);
        System.out.println("//////////////////////////////////////");
        System.out.println(id);
        NetworkManager.getInstance().addToQueueAndWait(con);
       
    }
    
}
