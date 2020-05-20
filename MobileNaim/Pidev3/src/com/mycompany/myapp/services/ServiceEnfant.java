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
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Enfant;
import com.mycompany.myapp.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author HEDI MSELMI
 */
public class ServiceEnfant {
       public ArrayList<Enfant> tasks;
    
    public static ServiceEnfant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceEnfant() {
         req = new ConnectionRequest();
    }

    public static ServiceEnfant getInstance() {
        if (instance == null) {
            instance = new ServiceEnfant();
        }
        return instance;
    }

        
    public boolean AjoutEnfant(Enfant enfant,int id) {
      ConnectionRequest con = new ConnectionRequest();
SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");// création d'une nouvelle demande de connexion   id_reclamation="+id_reclamation+"&description="+description
           String dd="test";
           String hhh="test";
           String gffff="test";
           String ppp="test";
           String ttt="test5";  
        String Url = "http://127.0.0.1/PidevF2/pidev/web/app_dev.php/Pidev/zaert/"+Statics.CONNECTED_USER.getIdUser()+"?nom="+enfant.getNom()+"&prenom="+enfant.getPrenom()+"&remarque="+enfant.getRemarque()+"&image="+enfant.getImage()+"&document="+enfant.getDocument()+"&cantine="+enfant.getCantine();
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
         //   System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return false; // Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }

   public ArrayList<Enfant> parseListReclamationJson(String json) {

        ArrayList<Enfant> listReclamations = new ArrayList<>();

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
                Enfant r = new Enfant();

               // int id_enfant = Integer.parseInt(obj.get("idEnfant").toString());
                
                 float id_enfant = Float.parseFloat(obj.get("idEnfant").toString());
               r.setId_enfant((int) id_enfant);
                  r.setNom(obj.get("nom").toString());
                 r.setPrenom(obj.get("prenom").toString());
                if(obj.get("idParent")!=null)
                {
                    if(obj.get("idParent").toString().contains("image="+Statics.CONNECTED_USER.getImage()))
                 {    r.setId_parent(Statics.CONNECTED_USER.getIdUser());  }
                 else{ r.setId_parent(0); }
                }
                 
                             //  System.out.println("connected user :"+Statics.CONNECTED_USER.toString());
                             //  System.out.println("****** :"+obj.get("idParent").toString());
                 // Date DateNaissance=null;
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                 
                try {
                    Date datenaissance;
                    datenaissance = formatter.parse(obj.get("datenaissance").toString());
                //    System.out.println("testina: "+datenaissance);
                    r.setDatenaissance(datenaissance);
                } catch (ParseException ex) {
                    
                }
                
             
                   
               
                r.setRemarque(obj.get("remarque").toString());
                r.setImage(obj.get("image").toString());
                r.setDocument(obj.get("document").toString());
//                String get = obj.get("idParent").toString();
            //    System.out.println("value: "+get);
           //     User s=new User(obj.get("idParent").toString());
           //    r.setId_parent(s);
                String tru="true";
                if(obj.get("cantine").toString()=="true")
                {  //float cantine = Float.parseFloat(obj.get("cantine").toString());
                r.setCantine(1);
                }
                 else{
                         r.setCantine(0);
                        }
                r.setStatus(obj.get("status").toString());
                
                 
              //  r.setCantine(obj.get("cantine"));
              //  System.out.println(r);
                
                listReclamations.add(r);

            }
              } catch (IOException ex) {
            
         }
     
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
      //  System.out.println(listReclamations);
        return listReclamations;
    }
    
      ArrayList<Enfant> listReclamations= new ArrayList<>();
    
    public ArrayList<Enfant> getListReclamations(){       
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://127.0.0.1/PidevF2/pidev/web/app_dev.php/Pidev/enfant/all";
        con.setUrl(url);
         con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEnfant ser = new ServiceEnfant();
                listReclamations = ser.parseListReclamationJson(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReclamations;
    }
    
    public ArrayList<Enfant> getEnfantsPourParent(int idParent){       
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL+"parsing/enfantParent/"+idParent;
        con.setUrl(url);
         con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEnfant ser = new ServiceEnfant();
                listReclamations = ser.parseListReclamationJson(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReclamations;
    }
    
    

   
    
    
  
    
}
