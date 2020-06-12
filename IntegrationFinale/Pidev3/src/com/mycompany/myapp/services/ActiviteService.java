/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.activite;
import com.mycompany.myapp.entities.ratingactivite;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ActiviteService {
    
   public boolean resultOK;
   public ArrayList<activite>  activite;
  
      public static ActiviteService instance=null;
    private ConnectionRequest req;

    private ActiviteService() {
         req = new ConnectionRequest();
    }

    public static ActiviteService getInstance() {
        if (instance == null) {
            instance = new ActiviteService();
        }
        return instance;
    }
   
   
   
   
   
   
   
   
   
   
   public ArrayList<activite> parseTasks(String jsonText){
        try {
            activite=new ArrayList<>();
            com.codename1.io.JSONParser j = new com.codename1.io.JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

        
            Map<String,Object> tasksListJson = j.parseJSON(new com.codename1.io.CharArrayReader(jsonText.toCharArray()));
            
             
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                activite t = new activite();
//                float id = Float.parseFloat(obj.get("idactivite").toString());
//                t.setIdActivite((int)id);
               
                t.setIntitule(obj.get("intitule").toString());
                t.setNiveau(obj.get("niveau").toString());
                t.setResponsable(obj.get("responsable").toString());
                
//                t.setType(obj.get("type").toString());
//                t.setHeureDebut(obj.get("heuredebut").toString());
//                t.setHeureFin(obj.get("heurefin").toString());

                //Ajouter la tâche extraite de la réponse Json à la liste
                activite.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu réc upérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return activite;
    }
   
   
  
   
   
    public boolean addRate(ratingactivite t) {
        String url = "http://localhost/PiDevF2/PiDev/web/app_dev.php/Pidev/activite/new?intitule="+t.getIntitule()+ "&rate=" +t.getRate(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
   
   
    public ArrayList<activite> getAllTasks(){
        String url ="http://localhost/PiDevF2/PiDev/web/app_dev.php/Pidev/activite/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                activite = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return activite;

    
 
    }
   
 
    
    
    public ArrayList<activite> getAllTasks2(String t){
             String url ="http://localhost/PiDevF2/PiDev/web/app_dev.php/Pidev/activite/all"+t;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                activite = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return activite;

    
    
    
    
    
    
    
    
    
    }
    
   

}

