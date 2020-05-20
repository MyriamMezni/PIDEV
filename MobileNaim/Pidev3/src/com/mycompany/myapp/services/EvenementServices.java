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
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evenement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


/**
 *
 * @author hamza
 */
public class EvenementServices {
    public Evenement E;
     public ArrayList<Evenement> events;
    public static EvenementServices instance;
    private ConnectionRequest req;

 private EvenementServices() {
        req=new ConnectionRequest(); 
    }
    
    public static EvenementServices getInstance(){
        if (instance==null)
            instance=new EvenementServices();
      return instance;
    
    }
    
   
     public ArrayList<Evenement> parseTasks(String jsonText){
        try {
             events=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Evenement e = new Evenement();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int)id);
               // e.setCapacite(((int)Float.parseFloat(obj.get("capacite").toString())));
                e.setNom(obj.get("nom").toString());
                 e.setType(obj.get("type").toString());
                   e.setDate_evt(obj.get("dateEvt").toString());
                   e.setHeure_d(obj.get("heureD").toString());
                  // e.setDepart(obj.get("depart").toString());
                  // e.setDestination(obj.get("destination").toString());
                 //  e.setLieu(obj.get("lieu").toString());
                 
                events.add(e);
            }
            
            
        } catch (IOException ex) {
            
        }
        return events;
    }
       public Evenement parseTask(String jsonText){
        try {
             E=new Evenement();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Evenement e = new Evenement();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int)id);
               // e.setCapacite(((int)Float.parseFloat(obj.get("capacite").toString())));
                e.setNom(obj.get("nom").toString());
               
              E=e;
            }
            
            
        } catch (IOException ex) {
            
        }
        return E;
    }
     
     
       public ArrayList<Evenement> getAllTasks(){
        String url = "http://localhost/PidevF2/pidev/web/app_dev.php/Pidev/Evenement/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
       public Evenement getone(){
       
       String url = "http://localhost/PidevF2/pidev/web/app_dev.php/Pidev/Evenement/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                E = parseTask(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return E;

       }


}