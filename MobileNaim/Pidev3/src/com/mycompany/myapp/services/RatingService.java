/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.ratingactivite;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class RatingService {
    
      public boolean resultOK;
   public ArrayList<ratingactivite>  ratingactivite;
  
      public static RatingService instance=null;
    private ConnectionRequest req;

    private RatingService() {
         req = new ConnectionRequest();
    }

    public static RatingService getInstance() {
        if (instance == null) {
            instance = new RatingService();
        }
        return instance;
    }
    
    
    
     
   public ArrayList<ratingactivite> parseTasks(String jsonText){
        try {
            ratingactivite=new ArrayList<>();
            com.codename1.io.JSONParser j = new com.codename1.io.JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

        
            Map<String,Object> tasksListJson = j.parseJSON(new com.codename1.io.CharArrayReader(jsonText.toCharArray()));
            
             
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                ratingactivite t = new ratingactivite();
//                float id = Float.parseFloat(obj.get("idactivite").toString());
//                t.setIdActivite((int)id);
               
                t.setIntitule(obj.get("intitule").toString());
                float id = Float.parseFloat(obj.get("rate").toString());
                t.setRate((int)id);
//                t.setType(obj.get("type").toString());
//                t.setHeureDebut(obj.get("heuredebut").toString());
//                t.setHeureFin(obj.get("heurefin").toString());

                //Ajouter la tâche extraite de la réponse Json à la liste
                ratingactivite.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return ratingactivite;
    }
   
   
  
   
   
     public ArrayList<ratingactivite> getAllTasks(){
        String url ="http://localhost/PidevF2/PiDev/web/app_dev.php/Pidev/activite/allRating";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ratingactivite = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ratingactivite;

    
 
    }
   
}
